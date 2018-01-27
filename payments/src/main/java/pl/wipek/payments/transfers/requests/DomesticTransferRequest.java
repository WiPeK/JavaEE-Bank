package pl.wipek.payments.transfers.requests;

import pl.wipek.shared.domain.entity.Beneficiary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DomesticTransferRequest extends TransferRequest {

    private List<Beneficiary> beneficiary = new ArrayList<>();

    public DomesticTransferRequest() {
    }

    @Override
    public List<Beneficiary> getBeneficiary() {
        return beneficiary;
    }

    @Override
    public void setBeneficiary(List<Beneficiary> beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DomesticTransferRequest that = (DomesticTransferRequest) o;
        return Objects.equals(beneficiary, that.beneficiary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), beneficiary);
    }

    @Override
    public String toString() {
        return super.toString() + "DomesticTransferRequest{" +
                "beneficiary=" + beneficiary +
                '}';
    }
}
