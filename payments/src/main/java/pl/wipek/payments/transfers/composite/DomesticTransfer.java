package pl.wipek.payments.transfers.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.payments.transfers.converters.DomesticTransferConverter;
import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.validators.DomesticTransferValidator;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.domain.entity.Transfer;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.emails.EmailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

public class DomesticTransfer extends TransferContainer {

    private static final Logger logger = LoggerFactory.getLogger(DomesticTransfer.class);

    private DomesticTransferRequest domesticTransferRequest;

    private TransactionBonus transactionBonus;

    private pl.wipek.shared.domain.entity.DomesticTransfer domesticTransfer;

    @Override
    public void add(TransferContainer transferContainer) {}

    @Override
    public void execute() throws Exception {
        super.execute();
        updateTransactionBonus();
    }

    private void updateTransactionBonus() {
        if (transactionBonus.getFreePayments() > -1) {
            paymentsDAO.updateTransactionBonus(transactionBonus);
        }
    }

    @Override
    protected boolean validate() throws Exception {

        DomesticTransferValidator domesticTransferValidator = new DomesticTransferValidator(domesticTransferRequest);

        boolean invalid = domesticTransferValidator.validate();
        return invalid;
    }

    @Override
    protected void convertMoney() throws Exception {
        logger.info("Converting is not necessary in domestic transfer");
    }

    @Override
    protected Double calculateCosts() throws Exception {
        DomesticTransferType domesticTransferType =
                paymentsDAO.getDomesticTransferTypeById(domesticTransferRequest.getType().getId());

        transactionBonus =
                paymentsDAO.getTransactionBonus(domesticTransferRequest.getUserAccount().getId());


        logger.info(transactionBonus.toString());
        boolean usePremium = false;

        if (Arrays.asList(DomesticTransferType.PREMIUM_TRANSFERS).contains(domesticTransferType.getValue())) {
            if (transactionBonus.getFreePremiumPayments() > 0) {
                int freePremiumTransactions = transactionBonus.getFreePremiumPayments() - 1;
                transactionBonus.setFreePremiumPayments(freePremiumTransactions);
            } else {
                setCosts(getCosts() + domesticTransferType.getCost() + domesticTransferRequest.getAmount());
            }
            usePremium = true;
        }

        if (!usePremium) {
            if (transactionBonus.getFreePayments() > 0) {
                int freeTransactions = transactionBonus.getFreePayments() - 1;
                transactionBonus.setFreePayments(freeTransactions);
            } else {
                setCosts(getCosts() + domesticTransferType.getCost() + domesticTransferRequest.getAmount());
            }
        }

        return getCosts();
    }

    @Override
    protected boolean save() throws DaoException {
        if (domesticTransferRequest.getErrors().size() > 0) {
            return false;
        }

        if (domesticTransferRequest.getUserAccount().getBalance() <= getCosts()) {
            domesticTransferRequest.getErrors().add("No money in account");
            return false;
        }

        domesticTransfer =
                DomesticTransferConverter.convertFromRequest(domesticTransferRequest);
        transfersDao.persist(domesticTransfer);
        Double transferCost = domesticTransfer.getAccount().getBalance() - (getCosts() + domesticTransfer.getAmount());
        domesticTransfer.getAccount().setBalance(transferCost);

        paymentsDAO.updateAccount(domesticTransfer.getAccount());
        domesticTransfer = transfersDao.persist(domesticTransfer);
        return true;
    }

    @Override
    protected void sendConfirmationEmail() {
        if (domesticTransferRequest.isEmailConfirm()) {
            try {
                EmailSender.sendEmail("Potwierdzenie przelewu", domesticTransfer.toString(), paymentsDAO.getCustomerEmailByAccountId(""));
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Set<Transfer> getTransfers() {
        Set<Transfer> transfers = new HashSet<>();
        transfers.add(domesticTransfer);
        return transfers;
    }

    public DomesticTransferRequest getDomesticTransferRequest() {
        return domesticTransferRequest;
    }

    public void setDomesticTransferRequest(DomesticTransferRequest domesticTransferRequest) {
        this.domesticTransferRequest = domesticTransferRequest;
    }

    @Override
    public Set<String> getErrors() {
        return domesticTransferRequest.getErrors();
    }

    @Override
    protected void addError(String error) {
        domesticTransferRequest.getErrors().add(error);
    }

    @Override
    public Set<TransferRequest> getTransferRequests() {
        Set<TransferRequest> transferRequests = new HashSet<>();
        transferRequests.add(domesticTransferRequest);
        return transferRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomesticTransfer that = (DomesticTransfer) o;
        return Objects.equals(domesticTransferRequest, that.domesticTransferRequest);
    }

    @Override
    public int hashCode() {

        return Objects.hash(domesticTransferRequest);
    }

    @Override
    public String toString() {
        return "DomesticTransfer{" +
                "domesticTransferRequest=" + domesticTransferRequest +
                '}';
    }
}
