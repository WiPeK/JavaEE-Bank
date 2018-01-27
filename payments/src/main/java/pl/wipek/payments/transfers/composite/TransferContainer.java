package pl.wipek.payments.transfers.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.payments.ejb.dao.TransfersDao;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.shared.domain.entity.Transfer;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Properties;
import java.util.Set;

public abstract class TransferContainer {

    @XmlTransient
    @JsonIgnore
    @EJB(beanInterface = PaymentsDAO.class, beanName = "PaymentsDaoImpl")
    protected PaymentsDAO paymentsDAO;

    @XmlTransient
    @JsonIgnore
    @EJB(beanInterface = TransfersDao.class, beanName = "TransfersDaoImpl")
    protected TransfersDao transfersDao;

    public TransferContainer() {
        Properties prop = new Properties();
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            Context context = new InitialContext(prop);
            paymentsDAO = (PaymentsDAO)context.lookup("java:global/app-api/PaymentsDaoImpl");
            transfersDao = (TransfersDao) context.lookup("java:global/app-api/TransfersDaoImpl");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private Double costs = 0.0;
    public abstract void add(TransferContainer transferContainer);

    /**
     * template method
     */
    public void execute() throws Exception {
        convertMoney();
        calculateCosts();
        Double balance = getTransferRequests().stream().findAny().map(i -> i.getUserAccount().getBalance()).get();
        if(getCosts() >= balance) {
            addError("No money in account");
            return;
        }
        if(!validate()) {
            addError("Validation error");
            return;
        }
        if(!save()) {
            addError("Saving error");
            return;
        }
        sendConfirmationEmail();
    }

    protected abstract boolean validate() throws Exception;

    protected abstract void convertMoney() throws Exception;
    protected abstract Double calculateCosts() throws Exception;
    protected abstract boolean save() throws Exception;
    protected abstract void sendConfirmationEmail();
    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }

    public abstract Set<String> getErrors();

    public abstract Set<Transfer> getTransfers();
    public abstract Set<TransferRequest> getTransferRequests();
    protected abstract void addError(String error);
}
