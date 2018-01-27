package pl.wipek.payments.transfers.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.shared.domain.entity.Transfer;
import pl.wipek.shared.emails.EmailSender;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DomesticTransferPackage extends TransferContainer {

    private static final Logger logger = LoggerFactory.getLogger(DomesticTransferPackage.class);

    private Set<TransferContainer> transfers = new HashSet<>();

    @Override
    public void add(@NotNull TransferContainer transferContainer) {
        transfers.add(transferContainer);
    }

    @Override
    protected boolean validate() throws Exception {
        boolean result = true;
        for (TransferContainer transfer : transfers) {
            result &= transfer.validate();
        }
        return result;
    }

    @Override
    protected void convertMoney() {
        logger.info("Method not implemented in domestic transfer");
    }

    @Override
    protected Double calculateCosts() {
        for (TransferContainer transfer : transfers) {
            try {
                setCosts(getCosts() + transfer.calculateCosts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return getCosts();
    }

    @Override
    protected boolean save() throws Exception {
        TransferContainer transferContainer = transfers.stream().findFirst().get();
        if (transferContainer instanceof DomesticTransfer) {
            if (getCosts() >= ((DomesticTransfer) transferContainer).getDomesticTransferRequest().getUserAccount().getBalance()) {
                ((DomesticTransfer) transferContainer).getDomesticTransferRequest().getErrors().add("No money in account");
                return false;
            }
        }

        for (TransferContainer transfer : transfers) {
            transfer.save();
        }
        return true;
    }

    @Override
    protected void sendConfirmationEmail() {
        StringBuilder message = new StringBuilder();
        for (TransferContainer transfer : transfers) {
            message.append(transfer.toString());
        }
        try {
            EmailSender.sendEmail("Potwierdzenie przelewu", message.toString(), "wipekxxx@gmail.com");
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<String> getErrors() {
        Set<String> errors = new HashSet<>();
        transfers.forEach(i -> errors.addAll(i.getErrors()));
        return errors;
    }

    public Set<Transfer> getTransfers() {
        Set<Transfer> transferContainers = new HashSet<>();
        transfers.forEach(i -> transferContainers.addAll(i.getTransfers()));
        return transferContainers;
    }

    public void setTransfers(Set<TransferContainer> transfers) {
        this.transfers = transfers;
    }

    @Override
    public Set<TransferRequest> getTransferRequests() {
        Set<TransferRequest> transferRequests = new HashSet<>();
        transfers.forEach(i -> transferRequests.addAll(i.getTransferRequests()));
        return transferRequests;
    }

    @Override
    protected void addError(String error) {
        for (TransferContainer transfer : transfers) {
            transfer.addError(error);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomesticTransferPackage that = (DomesticTransferPackage) o;
        return Objects.equals(transfers, that.transfers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(transfers);
    }

    @Override
    public String toString() {
        return "DomesticTransferPackage{" +
                "transfers=" + transfers +
                '}';
    }
}
