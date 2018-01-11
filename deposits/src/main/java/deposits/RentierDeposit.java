package deposits;

import pl.wipek.shared.domain.entity.DomesticTransfer;
import deposits.ejb.Raports.DepositStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentierDeposit implements Serializable, Deposit {

    private long id;

    private String name;

    private String accountNumber;

    private Date dateFrom;

    private Date dateTo;

    private BigDecimal amount;

    private String currency;

    private Date nextOutcomeDate;

    public RentierDeposit(long id, String name, String accountNumber, Date dateFrom, Date dateTo, BigDecimal amount, String currency) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.amount = amount;
        this.currency = currency;
    }

    public DomesticTransfer monthlyIncome(Date nextOutcomeDate){
        //TODO ADD MONTHLY INCOME LOGIC

        return new DomesticTransfer();
    }

    @Override
    public DepositStatus checkStatus() {
        return null;
    }

    @Override
    public DomesticTransfer transferFoundsToAccount() {
        return null;
    }

    @Override
    public DomesticTransfer transferFoundsFromAccount() {
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getNextOutcomeDate() {
        return nextOutcomeDate;
    }

    public void setNextOutcomeDate(Date nextOutcomeDate) {
        this.nextOutcomeDate = nextOutcomeDate;
    }
}
