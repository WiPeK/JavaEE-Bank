package pl.wipek.payments.transfers.strategy;

import pl.wipek.payments.transfers.composite.DomesticTransfer;
import pl.wipek.payments.transfers.composite.DomesticTransferPackage;
import pl.wipek.payments.transfers.composite.TransferContainer;
import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.response.DomesticTransferResponse;
import pl.wipek.payments.transfers.response.TransferResponse;

public class DomesticTransferStrategy implements TransferStrategy {
    @Override
    public TransferContainer createTransferContainer(TransferRequest transferRequest) {
        DomesticTransfer domesticTransfer = new DomesticTransfer();
        domesticTransfer.setDomesticTransferRequest((DomesticTransferRequest) transferRequest);
        return domesticTransfer;
    }

    @Override
    public TransferContainer createTransferPackage() {
        return new DomesticTransferPackage();
    }

    @Override
    public TransferResponse createResponse(TransferContainer transferContainer) {
        TransferResponse transferResponse = new DomesticTransferResponse();
        transferResponse.setTransferContainer(transferContainer);
        return transferResponse;
    }
}
