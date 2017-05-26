package top.auok.cbps.persistence.hibernate.gateway;

import javax.annotation.PostConstruct;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.management.Rate;
import top.auok.cbps.persistence.RateDAO;
import top.auok.cbps.persistence.hibernate.model.PersistentRate;
import top.auok.cbps.persistence.injection.annotation.Persistent;

@Decorator
public abstract class RateConverterDecorator extends GenericModelConverter<Rate, Long> implements RateDAO {

	@Inject @Delegate RateDAO delegate;

	@PostConstruct
	private void init() {
		super.entityClass = PersistentRate.class;
		super.delegate = this.delegate;
	}
	
	@Inject void setFactory(@Persistent CbpsObjectAdapterCopyFactory factory) {
		super.factory = factory;
	}
}
