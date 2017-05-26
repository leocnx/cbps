package top.auok.cbps.model.base.reference.rate;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateAdapter;
import top.auok.cbps.model.base.reference.AdapterReference;

public abstract class RateItemToRateReference<TT extends RateAdapter> extends AdapterReference<BaseRate, TT> {

	protected abstract BaseRateItem getBaseHolder();

	@Override
	protected void setReferenceOnDelegate(BaseRate baseTarget) {
		getBaseHolder().setRate(baseTarget);
	}

	@Override
	protected BaseRate getValueFromDelegate() {
		return getBaseHolder().getRate();
	}
}
