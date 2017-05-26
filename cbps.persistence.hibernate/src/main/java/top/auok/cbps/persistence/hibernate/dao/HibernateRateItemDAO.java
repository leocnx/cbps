package top.auok.cbps.persistence.hibernate.dao;

import javax.ejb.Stateless;

import top.auok.cbps.model.management.RateItem;
import top.auok.cbps.persistence.RateItemDAO;
import top.auok.cbps.persistence.hibernate.model.PersistentRateItem;
import top.auok.persistence.jpa.AbstractDAO;

@Stateless
public class HibernateRateItemDAO extends AbstractDAO<RateItem, PersistentRateItem, Long> implements RateItemDAO {

	public HibernateRateItemDAO() {
		super(PersistentRateItem.class);
	}
}
