package pl.wipek.payments.transfers.composite;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class DomesticTransferPackage extends TransferContainer {

    private Set<TransferContainer> transfers = new HashSet<>();

    @Override
    public void add(@NotNull TransferContainer transferContainer) {
        transfers.add(transferContainer);
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
}
