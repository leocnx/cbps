package top.auok.cbps.persistence.hibernate.model;

import java.beans.PropertyChangeEvent;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateItemAdapter;
import top.auok.cbps.model.base.reference.rate.RateItemToRateReference;
import top.auok.cbps.model.management.Rate;

@Entity
@Table(name = "RATE_ITEM")
public class PersistentRateItem extends PersistentBaseAdapter<BaseRateItem> implements RateItemAdapter {

	private PersistentRateItemToRateReference rate;

	PersistentRateItem(BaseRateItem delegate) {
		super(delegate);
		rate = new PersistentRateItemToRateReference();
	}

	public PersistentRateItem() {
		this(new BaseRateItem());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(BaseRateItem.RATE_STR)) {
			this.rate.reset();
		}
	}

	@Override
	@ManyToOne(optional = false)
	public PersistentRate getRate() {
		return rate.get();
	}

	@Override
	public void setRate(Rate rate) {
		this.rate.set(rate, true);
	}

	private class PersistentRateItemToRateReference extends RateItemToRateReference<PersistentRate> {
		@Override
		protected BaseRateItem getBaseHolder() {
			return delegate;
		}

		@Override
		protected PersistentRate createAdapterFor(BaseRate baseObject) {
			return new PersistentRate(baseObject);
		}
	}
}
