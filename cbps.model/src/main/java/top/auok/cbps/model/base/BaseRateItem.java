package top.auok.cbps.model.base;

import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;

public class BaseRateItem extends BaseCbpsObject implements RateItem {

	public static final String RATE_STR = "RateItem.rate";

	private Rate rate;

	@Override
	public BaseRate getRate() {
		return (BaseRate) rate;
	}

	@Override
	public void setRate(Rate baseRate) {
		pcs.firePropertyChange(RATE_STR, this.rate, this.rate = baseRate);
	}

}
