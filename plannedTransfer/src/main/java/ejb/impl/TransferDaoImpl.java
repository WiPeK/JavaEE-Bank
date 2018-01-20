package ejb.impl;

import ejb.TransferDao;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransfer;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.Transfer;

import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
public class TransferDaoImpl extends AbstractDao<String, ScheduledTransfer> implements TransferDao {
    @Override
    public Set<Transfer> getUserTransfers(String customerId) {
        return null;
    }

}
