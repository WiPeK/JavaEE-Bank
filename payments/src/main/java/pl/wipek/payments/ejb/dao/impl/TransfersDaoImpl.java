package pl.wipek.payments.ejb.dao.impl;

import pl.wipek.payments.ejb.dao.TransfersDao;
import pl.wipek.shared.domain.entity.DomesticTransfer;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;

@Stateless(name = "TransfersDaoImpl")
public class TransfersDaoImpl extends AbstractDao<String, DomesticTransfer> implements TransfersDao {
}
