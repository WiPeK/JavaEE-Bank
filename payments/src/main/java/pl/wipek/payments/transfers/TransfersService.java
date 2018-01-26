package pl.wipek.payments.transfers;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.payments.transfers.composite.TransferContainer;
import pl.wipek.payments.transfers.factory.TransferContainerFactory;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.response.TransferResponse;
import pl.wipek.payments.transfers.strategy.TransferStrategy;
import pl.wipek.payments.transfers.strategy.TransferStrategyFactory;
import pl.wipek.payments.types.services.TransferTypes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

@Stateless
public class TransfersService {

    @EJB(beanInterface = PaymentsDAO.class, beanName = "PaymentsDaoImpl")
    private PaymentsDAO paymentsDAO;

    public TransferResponse tryExecuteTransfer(@NotNull String userId, @NotNull TransferRequest transferRequest, @NotNull TransferTypes transferType) throws Exception {
        TransferResponse transferResponse = null;

        transferRequest.setUserAccount(paymentsDAO.getActualAccount(transferRequest.getUserAccount().getId()));
        TransferStrategy transferStrategy = TransferStrategyFactory.createTransferStrategy(transferType);
        TransferContainer transferContainer = TransferContainerFactory.createTransferContainer(transferRequest, transferStrategy);

        try {
            transferContainer.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        transferResponse = transferStrategy.createResponse(transferContainer);

        return transferResponse;
    }




}
