package top.auok.cbps.model.base;

import java.util.Set;

import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;

public class BaseRate extends BaseCbpsObject implements Rate {

	public static final String RATE_ITEMS = "Rate.rateItems";

	private String rateCode;
	private String memo;

	@Override
	public String getRateCode() {
		return rateCode;
	}

	@Override
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	private Set<RateItem> rateItems;

	@Override
	public Set<RateItem> getRateItems() {
		return rateItems;
	}

	@Override
	public void setRateItems(Set<RateItem> rateItems) {
		pcs.firePropertyChange(RATE_ITEMS, this.rateItems, this.rateItems = rateItems);
	}

}
