package pl.wipek.payments.transfers.response;

import java.util.HashSet;
import java.util.Set;

public class TransferResponse {
    public static String STATUS_OK = "STATUS_OK";
    public static String STATUS_ERROR = "STATUS_ERROR";

    private String status;
    private Double costs;

    private Set<pl.wipek.shared.domain.entity.Transfer> transfers;
    private Set<String> errors = new HashSet<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }

    public Set<pl.wipek.shared.domain.entity.Transfer> getTransfers() {
        return transfers;
    }

    public void setDomesticTransfers(Set<pl.wipek.shared.domain.entity.Transfer> transfers) {
        this.transfers = transfers;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }
}
