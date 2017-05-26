package top.auok.cbps.model.base.reference.rate;

import java.util.HashSet;
import java.util.Set;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateItemAdapter;
import top.auok.cbps.model.base.reference.AdapterReferenceCollection;
import top.auok.cbps.model.management.RateItem;

public abstract class RateToRateItemReference<TT extends RateItemAdapter>
		extends AdapterReferenceCollection<RateItem, BaseRateItem, TT, Set<RateItem>> {

	protected abstract BaseRate getBaseHolder();

	@Override
	protected void setReferenceOnDelegate(Set<RateItem> baseTarget) {
		getBaseHolder().setRateItems(baseTarget);
	}

	@Override
	protected Set<RateItem> getValueFromDelegate() {
		return getBaseHolder().getRateItems();
	}

	@Override
	protected Set<RateItem> createCollection() {
		//return new TreeSet<>();
		return new HashSet<>();
	}

	@Override
	protected void setBackReferenceOf(BaseRateItem target) {
		target.setRate(getBaseHolder());
	}
}
