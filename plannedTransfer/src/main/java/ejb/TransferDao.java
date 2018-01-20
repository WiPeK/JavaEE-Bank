package ejb;

import pl.wipek.shared.ejb.dao.Dao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransfer;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.Transfer;

import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
public interface TransferDao extends Dao<String , ScheduledTransfer> {
    public Set<Transfer> getUserTransfers(String customerId);
}
