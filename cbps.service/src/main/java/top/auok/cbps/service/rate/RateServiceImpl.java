package top.auok.cbps.service.rate;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;
import top.auok.cbps.persistence.RateDAO;
import top.auok.cbps.persistence.RateItemDAO;
import top.auok.cbps.persistence.injection.annotation.Persistent;

@RequestScoped
public class RateServiceImpl implements RateService {

	@Inject
	@Persistent
	private CbpsObjectAdapterFactory factory;

	@Inject
	private RateDAO rateDAO;

	@Inject
	private RateItemDAO rateItemDAO;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Long createRate(Rate rate) throws InvalidRateException {
		// persistentAdapterFactory.setAdaptee(rate);
		Rate persistentRate = factory.createRate();
		// if (rate.getRateItems() == null) {
		// throw new EmptyException();
		// }
		persistentRate.setRateCode(rate.getRateCode());

		// persistentAdapterFactory.setAdaptee(rate.getRateItems().iterator().next());
		rateDAO.create(persistentRate);

		RateItem rateItem = factory.createRateItem();
		rateItem.setRate(persistentRate);
		rateItemDAO.create(rateItem);
		// int i = 1 / 0;
		Long id = persistentRate.getId();
		return id;
		// return persistentRate;
		// return rateDAO.findById(persistentRate.getId());
	}

	@Override
	public Long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex, int resultsNumber)
			throws InvalidRateException {

		return rateDAO.findByParameter(receivingList, id, rateCode, startIndex, resultsNumber);
	}

}
