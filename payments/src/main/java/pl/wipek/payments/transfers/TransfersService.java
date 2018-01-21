package pl.wipek.payments.transfers;

import pl.wipek.payments.transfers.composite.TransferContainer;
import pl.wipek.payments.transfers.factory.TransferContainerFactory;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.response.TransferResponse;
import pl.wipek.payments.transfers.strategy.TransferStrategy;
import pl.wipek.payments.transfers.strategy.TransferStrategyFactory;
import pl.wipek.payments.types.services.TransferTypes;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

@Stateless
public class TransfersService {

    public TransferResponse tryExecuteTransfer(@NotNull String userId, @NotNull TransferRequest transferRequest, @NotNull TransferTypes transferType) {
        TransferResponse transferResponse = null;
        try {
            TransferStrategy transferStrategy = TransferStrategyFactory.createTransferStrategy(transferType);
            TransferContainer transferContainer =
                    TransferContainerFactory.createTransferContainer(transferRequest, transferStrategy);
            transferContainer.execute();
            transferResponse = transferStrategy.createResponse(transferContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transferResponse;
    }




}
