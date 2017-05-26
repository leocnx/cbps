package top.auok.cbps.web.service.model.injection.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.persistence.RateDAO;
import top.auok.cbps.web.service.model.injection.anotation.JSONized;

@Dependent
@JSONized
public class JSONRateDAO extends GenericJSONDAO<Rate, Long> implements RateDAO {

	@Inject
	void setCopyFactory(CbpsObjectAdapterCopyFactory copyFactory) {
		super.copyFactory = copyFactory;
	}

	@Inject
	void setDelegate(RateDAO delegate) {
		super.delegate = delegate;
	}

	@Override
	public long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex,
			int resultsNumber) {
		Long count = ((RateDAO) delegate).findByParameter(receivingList, id, rateCode, startIndex, resultsNumber);
		for (int i = 0; i < receivingList.size(); i++) {
			receivingList.set(i, jsonCopy(receivingList.get(i)));
		}
		return count;
	}
}
