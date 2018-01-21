package pl.wipek.payments.transfers.factory;

import pl.wipek.payments.transfers.composite.*;
import pl.wipek.payments.transfers.requests.TransferRequest;
import pl.wipek.payments.transfers.strategy.TransferStrategy;
import pl.wipek.shared.domain.entity.interfaces.Beneficiary;

import java.util.Collections;
import java.util.HashSet;

public class TransferContainerFactory {

    public static TransferContainer createTransferContainer(TransferRequest transferRequest, TransferStrategy transferStrategy) {
        return checkIsTransfersPackage(transferRequest) ?
                createPackage(transferRequest, transferStrategy) :
                createOneTransfer(transferRequest, transferStrategy);
    }

    private static boolean checkIsTransfersPackage(TransferRequest transferRequest) {
        return transferRequest.getBeneficiaries().size() > 1;
    }

    private static TransferContainer createOneTransfer(TransferRequest transferRequest, TransferStrategy transferStrategy) {
        return transferStrategy.createTransferContainer(transferRequest);
    }

    private static TransferContainer createPackage(TransferRequest transferRequest, TransferStrategy transferStrategy) {
        TransferContainer transferContainer = transferStrategy.createTransferPackage();
        Beneficiary[] beneficiaries = transferRequest.getBeneficiaries().stream().toArray(Beneficiary[]::new);
        TransferRequest[] transferRequests = new TransferRequest[beneficiaries.length];
        for (int i = 0; i < beneficiaries.length; i++) {
            transferRequests[i].setBeneficiaries(new HashSet<>(Collections.singletonList(beneficiaries[i])));
            transferContainer.add(createOneTransfer(transferRequests[i], transferStrategy));
        }
        return transferContainer;
    }

}
