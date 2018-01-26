package pl.wipek.payments.transfers.strategy;

import pl.wipek.payments.transfers.composite.ForeignTransfer;
import pl.wipek.payments.transfers.composite.ForeignTransferPackage;
import pl.wipek.payments.transfers.composite.TransferContainer;
import pl.wipek.payments.transfers.requests.ForeignTransferRequest;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.response.ForeignTransferResponse;
import pl.wipek.payments.transfers.response.TransferResponse;

public class ForeignTransferStrategy implements TransferStrategy {
    @Override
    public TransferContainer createTransferContainer(TransferRequest transferRequest) {
        ForeignTransfer foreignTransfer = new ForeignTransfer();
//        foreignTransfer.setForeignTransferRequest((ForeignTransferRequest) transferRequest);
        return null;
    }

    @Override
    public TransferContainer createTransferPackage() {
        return null;
    }

    @Override
    public TransferResponse createResponse(TransferContainer transferContainer) {
        TransferResponse transferResponse = new ForeignTransferResponse();
        return transferResponse;
    }
}
