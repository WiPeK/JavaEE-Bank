package ejb.services;

import ejb.dao.TransferDao;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */
@Stateless
public class TransferService implements Serializable {

    @EJB(beanInterface = TransferDao.class, beanName = "AccountsDaoImpl")
    private TransferDao accountsDao;

    public Set<ScheduledTransferShared> getTransfers(String customerId) {
        return accountsDao.getUserTransfers(customerId);
    }
}