package top.auok.cbps.persistence.hibernate.model;

import java.beans.PropertyChangeEvent;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.collection.spi.PersistentCollection;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateAdapter;
import top.auok.cbps.model.base.reference.rate.RateToRateItemReference;
import top.auok.cbps.model.management.RateItem;

@Entity
@Table(name = "RATE")
public class PersistentRate extends PersistentBaseAdapter<BaseRate> implements RateAdapter {

	private PersistentRateToRateItemReference rateItem;

	PersistentRate(BaseRate delegate) {
		super(delegate);
		rateItem = new PersistentRateToRateItemReference();
	}

	public PersistentRate() {
		this(new BaseRate());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case BaseRate.RATE_ITEMS:
			this.rateItem.reset();
			break;
		}
	}

	/**
	 * 通过rate 获取rateItem的时候，需要通过此方法才能取得item
	 */
	@PostLoad
	void updateBase() {
		this.rateItem.get().size();
		this.rateItem.pushCacheDown(true);
	}

	@Override
	public String getRateCode() {
		return delegate.getRateCode();
	}

	@Override
	public void setRateCode(String rateCode) {
		delegate.setRateCode(rateCode);
	}

	@Override
	public String getMemo() {
		return delegate.getMemo();
	}

	@Override
	public void setMemo(String memo) {
		delegate.setMemo(memo);
	}

	private class PersistentRateToRateItemReference extends RateToRateItemReference<PersistentRateItem> {
		@Override
		protected BaseRate getBaseHolder() {
			return delegate;
		}

		@Override
		protected PersistentRateItem createAdapterFor(BaseRateItem baseObject) {
			return new PersistentRateItem(baseObject);
		}

		@Override
		protected void setBackReferenceOf(BaseRateItem target) {
			target.setRate(delegate);
		}

		@Override
		public void set(Set<RateItem> newCollection, boolean cache) {
			if (newCollection instanceof PersistentCollection
					&& !((PersistentCollection) newCollection).wasInitialized()) {
				this.cache(newCollection);
			} else {
				super.set(newCollection, cache);
			}
		}

	}

	@Override
	@OneToMany(targetEntity = PersistentRateItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "rate", orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	public Set<RateItem> getRateItems() {
		return rateItem.get();
	}

	@Override
	public void setRateItems(Set<RateItem> rateItems) {
		rateItem.set(rateItems, true);
	}

}
