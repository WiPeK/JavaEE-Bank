package pl.wipek.payments.transfers.composite;

public abstract class TransferContainer {

    public abstract void add(TransferContainer transferContainer);
    public abstract void prepare();
    public abstract Double getCosts();

    /**
     * template method
     */
    public void execute() {
        validate();
        convertMoney();
        calculateCosts();
        saveAsTemplate();
        generatePdf();
    }

    protected abstract boolean validate();
    protected abstract void convertMoney();
    protected abstract Double calculateCosts();
    protected abstract boolean saveAsTemplate();
    protected abstract String generatePdf();
}
