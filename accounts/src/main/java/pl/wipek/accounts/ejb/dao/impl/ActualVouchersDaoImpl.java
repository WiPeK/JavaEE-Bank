package pl.wipek.accounts.ejb.dao.impl;

import pl.wipek.accounts.ejb.dao.ActualVouchersDao;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

@Stateless(name = "ActualVouchersDaoImpl", mappedName = "ActualVouchersDaoImpl")
public class ActualVouchersDaoImpl extends AbstractDao<String, ActualVoucher> implements ActualVouchersDao {

}