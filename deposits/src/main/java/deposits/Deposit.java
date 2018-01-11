package deposits;

import pl.wipek.shared.domain.entity.DomesticTransfer;
import deposits.ejb.Raports.DepositStatus;

public interface Deposit {
    public DepositStatus checkStatus();

    public DomesticTransfer transferFoundsToAccount();

    public DomesticTransfer transferFoundsFromAccount();

}
