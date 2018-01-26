package pl.wipek.payments.transfers.requests;

import pl.wipek.shared.domain.entity.Beneficiary;

import java.util.List;
import java.util.Set;

public class ForeignTransferRequest extends TransferRequest {
    @Override
    public List<Beneficiary> getBeneficiary() {
        return null;
    }

    @Override
    public void setBeneficiary(List<Beneficiary> beneficiary) {
    }
}
