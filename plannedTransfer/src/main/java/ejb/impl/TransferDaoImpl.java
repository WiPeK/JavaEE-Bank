package ejb.impl;

import ejb.TransferDao;
import javassist.NotFoundException;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.shared.transfer.ScheduledTransfer;
import pl.wipek.shared.transfer.Transfer;

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
