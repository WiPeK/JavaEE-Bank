package pl.wipek.payments.transfers.strategy;

import pl.wipek.payments.types.services.TransferTypes;

public class TransferStrategyFactory {

    public static TransferStrategy createTransferStrategy(TransferTypes transferType) throws Exception {
        TransferStrategy transferStrategy = null;
        switch (transferType) {
            case DOMESTIC:
                transferStrategy = new DomesticTransferStrategy();
                break;
            case FOREIGN:
                transferStrategy = new ForeignTransferStrategy();
                break;
            default:
                throw new Exception("Can not create strategy for this type");
        }
        return transferStrategy;
    }
}
