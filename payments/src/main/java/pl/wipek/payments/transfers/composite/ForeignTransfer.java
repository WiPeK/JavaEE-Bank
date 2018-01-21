package pl.wipek.payments.transfers.composite;

import pl.wipek.payments.transfers.requests.ForeignTransferRequest;

public class ForeignTransfer extends TransferContainer {
    private ForeignTransferRequest foreignTransferRequest;

    @Override
    public void add(TransferContainer transferContainer) {

    }

    @Override
    public void prepare() {

    }

    @Override
    public Double getCosts() {
        return null;
    }

    @Override
    protected boolean validate() {
        return false;
    }

    @Override
    protected void convertMoney() {

    }

    @Override
    protected Double calculateCosts() {
        return null;
    }

    @Override
    protected boolean saveAsTemplate() {
        return false;
    }

    @Override
    protected String generatePdf() {
        return null;
    }

    public ForeignTransferRequest getForeignTransferRequest() {
        return foreignTransferRequest;
    }

    public void setForeignTransferRequest(ForeignTransferRequest foreignTransferRequest) {
        this.foreignTransferRequest = foreignTransferRequest;
    }
}
