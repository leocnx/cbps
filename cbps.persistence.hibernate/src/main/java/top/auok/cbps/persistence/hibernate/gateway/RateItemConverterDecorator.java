package top.auok.cbps.persistence.hibernate.gateway;

import javax.annotation.PostConstruct;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.management.RateItem;
import top.auok.cbps.persistence.RateItemDAO;
import top.auok.cbps.persistence.hibernate.model.PersistentRateItem;
import top.auok.cbps.persistence.injection.annotation.Persistent;

@Decorator
public abstract class RateItemConverterDecorator extends GenericModelConverter<RateItem, Long> implements RateItemDAO {

	@Inject
	@Delegate
	RateItemDAO delegate;

	@PostConstruct
	private void init() {
		super.entityClass = PersistentRateItem.class;
		super.delegate = this.delegate;
	}

	@Inject
	void setFactory(@Persistent CbpsObjectAdapterCopyFactory factory) {
		super.factory = factory;
	}
}
