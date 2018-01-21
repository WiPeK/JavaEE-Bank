package ejb.dao.impl;

import ejb.dao.TransferDao;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
@Stateless(name = "TransferDaoImpl", mappedName = "TransferDaoImpl")
public class TransferDaoImpl extends AbstractDao<String, ScheduledTransferShared> implements TransferDao {
    @Override
    public Set<ScheduledTransferShared> getUserTransfers(String userID) {

        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e where e.account.id =:userID").setParameter("userID",userID);

        return new HashSet<>(query.getResultList());
    }

}
