package top.auok.cbps.model.base.adapter;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateAdapter;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateItemAdapter;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;

public class BaseCbpsObjectAdapterCopyFactory implements CbpsObjectAdapterCopyFactory {

	private Object adaptee;

	private CbpsObjectAdapterFactory factory;

	protected void setFactory(CbpsObjectAdapterFactory factory) {
		this.factory = factory;
	}

	public CbpsObjectAdapterCopyFactory setAdaptee(Object adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	@Override
	public Rate createRate() {
		final RateAdapter result = factory.createRate();
		result.adapt(((RateAdapter) adaptee).unwrap());
		return result;
	}

	@Override
	public RateItem createRateItem() {
		final RateItemAdapter result = factory.createRateItem();
		result.adapt(((RateItemAdapter) adaptee).unwrap());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T createAdapterFor(T adaptee) {
		setAdaptee(adaptee);

		if (adaptee instanceof Rate) {
			return (T) createRate();
		}

		if (adaptee instanceof RateItem) {
			return (T) createRateItem();
		}

		return null;
	}

}
