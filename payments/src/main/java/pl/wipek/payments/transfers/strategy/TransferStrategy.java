package pl.wipek.payments.transfers.strategy;

import pl.wipek.payments.transfers.composite.TransferContainer;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.response.TransferResponse;

public interface TransferStrategy {
    public TransferContainer createTransferContainer(TransferRequest transferRequest);
    public TransferContainer createTransferPackage();
    public TransferResponse createResponse(TransferContainer transferContainer);
}
