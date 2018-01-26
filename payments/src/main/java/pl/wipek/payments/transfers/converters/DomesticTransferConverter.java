package pl.wipek.payments.transfers.converters;

import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.shared.domain.entity.Beneficiary;
import pl.wipek.shared.domain.entity.DomesticBeneficiary;
import pl.wipek.shared.domain.entity.DomesticTransfer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DomesticTransferConverter {

    public static pl.wipek.shared.domain.entity.DomesticTransfer convertFromRequest(DomesticTransferRequest domesticTransferRequest) {
        pl.wipek.shared.domain.entity.DomesticTransfer domesticTransfer = new DomesticTransfer();
        domesticTransfer.setAccount(domesticTransferRequest.getUserAccount());

        List<Beneficiary> beneficiaries = domesticTransferRequest.getBeneficiary();
        Set<DomesticBeneficiary> domesticBeneficiaries = new HashSet<>();
        beneficiaries.forEach(i -> domesticBeneficiaries.add((DomesticBeneficiary) i));
        domesticTransfer.setDomesticBeneficiaries(domesticBeneficiaries);

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
