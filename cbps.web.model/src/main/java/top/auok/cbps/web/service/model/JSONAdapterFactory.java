package top.auok.cbps.web.service.model;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory;

@RequestScoped
public class JSONAdapterFactory implements CbpsObjectAdapterFactory {

	@Inject
	private Instance<JSONRate> rate;

	@Inject
	private Instance<JSONRateItem> rateItem;

	@Override
	public JSONRate createRate() {
		return rate.get();
	}

	@Override
	public JSONRateItem createRateItem() {
		return rateItem.get();
	}

}
