package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DOMESTIC_TRANSFERS")
@XmlRootElement
public class DomesticTransfer implements Serializable, Transfer {
    public static final Double MIN_TRANSFER_VALUE = 1.0;

    private String id;
    private Account account;
    private DomesticTransferType type;
    private Double amount;
    private String title;
    private Date date;
    private boolean template;

    private Set<Beneficiary> beneficiaries = new HashSet<>();

    public DomesticTransfer() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GenericGenerator(name = "db-uuid", strategy = "guid")
    @GeneratedValue(generator = "db-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "domesticTransfer", targetEntity = Beneficiary.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    public Set<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    @XmlTransient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOMESTIC_TRANSFER_TYPES_ID")
    public DomesticTransferType getType() {
        return type;
    }

    public void setType(DomesticTransferType type) {
        this.type = type;
    }

    @Column(name = "AMOUNT")
    @NotNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "TITLE")
    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "TDATE")
    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "TEMPLATE")
    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomesticTransfer that = (DomesticTransfer) o;
        return template == that.template &&
                Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(title, that.title) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, amount, title, date, template);
    }

    @Override
    public String toString() {
        return "DomesticTransfer{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", template=" + template +
                '}';
    }
}
