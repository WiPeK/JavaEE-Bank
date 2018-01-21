package pl.wipek.payments.transfers.composite;

import pl.wipek.payments.transfers.requests.DomesticTransferRequest;

public class DomesticTransfer extends TransferContainer {
    private DomesticTransferRequest domesticTransferRequest;

    @Override
    public void add(TransferContainer transferContainer) {

    }

    @Override
    public void prepare() {

    }

    @Override
    public void execute() {

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

    @Override
    public Double getCosts() {
        return null;
    }

    public DomesticTransferRequest getDomesticTransferRequest() {
        return domesticTransferRequest;
    }

    public void setDomesticTransferRequest(DomesticTransferRequest domesticTransferRequest) {
        this.domesticTransferRequest = domesticTransferRequest;
    }
}
