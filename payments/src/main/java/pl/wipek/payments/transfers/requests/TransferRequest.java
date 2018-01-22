package pl.wipek.payments.transfers.requests;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.domain.entity.interfaces.Beneficiary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TransferRequest {

    @XmlElement(name = "userAccount")
    private Account userAccount;

    @XmlElement(name = "beneficiary")
    private Set<Beneficiary> beneficiaries;

    private DomesticTransferType type;

    private Double amount;

    private String title;

    private String date;

    private boolean template;

    private boolean emailConfirm;

    private String email;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public DomesticTransferType getType() {
        return type;
    }

    public void setType(DomesticTransferType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public boolean isEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(boolean emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return template == that.template &&
                emailConfirm == that.emailConfirm &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(beneficiaries, that.beneficiaries) &&
                Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(title, that.title) &&
                Objects.equals(date, that.date) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userAccount, beneficiaries, type, amount, title, date, template, emailConfirm, email);
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "account=" + userAccount +
                ", beneficiary=" + beneficiaries +
                ", type=" + type +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", template=" + template +
                ", emailConfirm=" + emailConfirm +
                ", email='" + email + '\'' +
                '}';
    }
}
