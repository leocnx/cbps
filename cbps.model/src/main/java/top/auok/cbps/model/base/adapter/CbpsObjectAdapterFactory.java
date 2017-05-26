package top.auok.cbps.model.base.adapter;

import top.auok.cbps.model.CbpsObjectFactory;
import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;

public interface CbpsObjectAdapterFactory extends CbpsObjectFactory {

	public interface RateAdapter extends Rate, Adapter<BaseRate> {
	}

	@Override
	RateAdapter createRate();

	public interface RateItemAdapter extends RateItem, Adapter<BaseRateItem> {
	}

	@Override
	RateItemAdapter createRateItem();
}
