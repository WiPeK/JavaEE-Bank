package pl.wipek.payments.transfers.validators;

import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.shared.validators.DateValidator;

import javax.validation.constraints.NotNull;

public class DomesticTransferValidator {

    private DomesticTransferRequest domesticTransferRequest;

    public DomesticTransferValidator(@NotNull DomesticTransferRequest domesticTransferRequest) {
        this.domesticTransferRequest = domesticTransferRequest;
    }

    public boolean validate() {
        return validateAccount() &&
            validateBeneficiary() &&
            validateAmount() &&
            validateType() &&
            validateTitle() &&
            validateDate();
    }

    private boolean validateDate() {
        if (!DateValidator.checkIsNotDateInPast(domesticTransferRequest.getDate())) {
            domesticTransferRequest.getErrors().add("Invalid transfer date");
            return false;
        }
        return true;
    }

    private boolean validateTitle() {
        if (domesticTransferRequest.getTitle() == null) {
            domesticTransferRequest.getErrors().add("Empty transfer title");
            return false;
        } else if(domesticTransferRequest.getTitle().length() > 50) {
            domesticTransferRequest.getErrors().add("Too long transfer title");
            return false;
        }
        return true;
    }

    private boolean validateType() {
        if (domesticTransferRequest.getType() == null) {
            domesticTransferRequest.getErrors().add("Invalid transfer type");
            return false;
        }
        return true;
    }

    private boolean validateAccount() {
        if (domesticTransferRequest.getUserAccount() == null) {
            domesticTransferRequest.getErrors().add("Invalid account");
            return false;
        }
        return true;
    }

    private boolean validateBeneficiary() {
        if (domesticTransferRequest.getBeneficiary().size() == 0) {
            domesticTransferRequest.getErrors().add("Invalid beneficiary");
            return false;
        }
        return true;
    }

    private boolean validateAmount() {
        if (domesticTransferRequest.getAmount() < pl.wipek.shared.domain.entity.DomesticTransfer.MIN_TRANSFER_VALUE) {
            domesticTransferRequest.getErrors().add("Too low transfer amount");
            return false;
        } else if(domesticTransferRequest.getAmount() >= domesticTransferRequest.getUserAccount().getBalance()) {
            domesticTransferRequest.getErrors().add("No money in account");
            return false;
        }
        return true;
    }
}
