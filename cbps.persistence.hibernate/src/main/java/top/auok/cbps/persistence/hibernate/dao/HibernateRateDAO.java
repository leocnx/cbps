package top.auok.cbps.persistence.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.Predicate;

import top.auok.cbps.model.management.Rate;
import top.auok.cbps.persistence.RateDAO;
import top.auok.cbps.persistence.hibernate.model.PersistentRate;
import top.auok.persistence.jpa.AbstractDAO;

@Stateless
public class HibernateRateDAO extends AbstractDAO<Rate, PersistentRate, Long> implements RateDAO {

	public HibernateRateDAO() {
		super(PersistentRate.class);
	}

	@Override
	public long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex,
			int resultsNumber) {
		return super.findAllWhere(receivingList, (criteriabuilder, root) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (id != null && !"".equals(id)) {
				predicates.add(criteriabuilder.equal(root.get("id"), id));
			}
			if (rateCode != null && !"".equals(rateCode)) {
				predicates.add(criteriabuilder.equal(root.get("rateCode"), rateCode));
			}
			return criteriabuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		}, (criteriabuilder, root) -> criteriabuilder.desc(root.get("rateCode")), startIndex, resultsNumber);
	}
}
