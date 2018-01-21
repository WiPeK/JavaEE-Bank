package pl.wipek.payments.transfers.response;

import pl.wipek.payments.transfers.composite.TransferContainer;

public abstract class TransferResponse {
    private TransferContainer transferContainer;

    public TransferContainer getTransferContainer() {
        return transferContainer;
    }

    public void setTransferContainer(TransferContainer transferContainer) {
        this.transferContainer = transferContainer;
    }
}
