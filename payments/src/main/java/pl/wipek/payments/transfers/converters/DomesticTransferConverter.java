package pl.wipek.payments.transfers.converters;

import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.shared.domain.entity.Beneficiary;
import pl.wipek.shared.domain.entity.DomesticTransfer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomesticTransferConverter {

    public static pl.wipek.shared.domain.entity.DomesticTransfer convertFromRequest(DomesticTransferRequest domesticTransferRequest) {
        pl.wipek.shared.domain.entity.DomesticTransfer domesticTransfer = new DomesticTransfer();
        domesticTransfer.setAccount(domesticTransferRequest.getUserAccount());

        List<Beneficiary> beneficiaries = domesticTransferRequest.getBeneficiary();
        Set<Beneficiary> beneficiarySet= new HashSet<>();
        beneficiarySet.addAll(beneficiaries);
        domesticTransfer.setBeneficiaries(beneficiarySet);

        domesticTransfer.setAmount(domesticTransferRequest.getAmount());

        domesticTransfer.setTitle(domesticTransferRequest.getTitle());

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            domesticTransfer.setDate(formatter.parse(domesticTransferRequest.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        domesticTransfer.setTemplate(domesticTransferRequest.isTemplate());

        domesticTransfer.setType(domesticTransferRequest.getType());

        return domesticTransfer;
    }
}
