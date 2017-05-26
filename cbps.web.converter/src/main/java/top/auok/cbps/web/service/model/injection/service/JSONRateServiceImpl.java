package top.auok.cbps.web.service.model.injection.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.service.rate.InvalidRateException;
import top.auok.cbps.service.rate.RateService;
import top.auok.cbps.web.service.model.injection.anotation.JSONized;

@RequestScoped
@JSONized
public class JSONRateServiceImpl implements RateService {

	@Inject
	RateService delegate;

	@Inject
	CbpsObjectAdapterCopyFactory copyFactory;

	@Override
	public Long createRate(Rate rate) throws InvalidRateException {
		return delegate.createRate(rate);
	}

	@Override
	public Long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex, int resultsNumber)
			throws InvalidRateException {
		Long count = delegate.findByParameter(receivingList, id, rateCode, startIndex, resultsNumber);
		for (int i = 0; i < receivingList.size(); i++) {
			receivingList.set(i, copyFactory.createAdapterFor(receivingList.get(i)));
		}
		return count;
	}

}
