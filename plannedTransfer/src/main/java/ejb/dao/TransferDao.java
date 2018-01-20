package ejb.dao;

import pl.wipek.shared.ejb.dao.Dao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;

import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
public interface TransferDao extends Dao<String , ScheduledTransferShared> {
    
    public Set<ScheduledTransferShared> getUserTransfers(String customerId);
}
