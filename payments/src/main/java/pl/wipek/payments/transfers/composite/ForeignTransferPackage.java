package pl.wipek.payments.transfers.composite;

public class ForeignTransferPackage extends TransferContainer {
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
}
