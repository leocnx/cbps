package top.auok.cbps.persistence.hibernate.model;

import javax.enterprise.context.RequestScoped;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory;
import top.auok.cbps.persistence.injection.annotation.Persistent;

@RequestScoped
@Persistent
public class PersistentAdapterFactory implements CbpsObjectAdapterFactory {

	@Override
	public PersistentRate createRate() {
		return new PersistentRate();
	}

	@Override
	public PersistentRateItem createRateItem() {
		return new PersistentRateItem();
	}

}
