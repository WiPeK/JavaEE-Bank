package ejb;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.Dao;
import pl.wipek.shared.transfer.ScheduledTransfer;
import pl.wipek.shared.transfer.Transfer;

import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
public interface TransferDao extends Dao<String , ScheduledTransfer> {
    public Set<Transfer> getUserTransfers(String customerId);
}
