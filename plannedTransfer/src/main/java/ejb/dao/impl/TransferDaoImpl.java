package ejb.dao.impl;

import ejb.dao.TransferDao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;

/**
 * Created by Michał on 20.01.2018.
 */
@Stateless(name = "PlannedTransferDaoImpl", mappedName = "PlannedTransferDaoImpl")
public class TransferDaoImpl extends AbstractDao<String, ScheduledTransferShared> implements TransferDao {
}
