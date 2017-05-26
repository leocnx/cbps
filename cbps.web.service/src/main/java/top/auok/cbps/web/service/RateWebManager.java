package top.auok.cbps.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.persistence.RateDAO;
import top.auok.cbps.persistence.injection.annotation.Persistent;
import top.auok.cbps.service.rate.InvalidRateException;
import top.auok.cbps.service.rate.RateService;
import top.auok.cbps.web.service.model.JSONPagedResults;
import top.auok.cbps.web.service.model.JSONRate;
import top.auok.cbps.web.service.model.injection.anotation.JSONized;

@RequestScoped
public class RateWebManager implements RateManagerWebResource {

	@Inject
	@JSONized
	private RateDAO rateDAO;

	@Inject
	@JSONized
	private RateService rateService;

	@Inject
	@Persistent
	private CbpsObjectAdapterCopyFactory persistentAdapterFactory;

	@Override
	public JSONRate createRate(JSONRate newRate) throws InvalidRateException {
		return rate(rateService.createRate(newRate));
	}

	@Override
	public JSONRate rate(Long id) {
		return (JSONRate) rateDAO.findById(id);
	}

	@Override
	public JSONPagedResults<JSONRate> list(String id, String rateCode, int pageNumber, int pageSize)
			throws InvalidRateException {
		List<Rate> list = new ArrayList<>();
		Long count = rateService.findByParameter(list, id, rateCode, pageNumber * pageSize, pageSize);
		return new JSONPagedResults<>(count, pageNumber, pageSize, list);
	}

}
