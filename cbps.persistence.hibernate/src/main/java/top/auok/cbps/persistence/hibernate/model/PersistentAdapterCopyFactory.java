package top.auok.cbps.persistence.hibernate.model;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.BaseCbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory;
import top.auok.cbps.persistence.injection.annotation.Persistent;

@Dependent
@Persistent
public class PersistentAdapterCopyFactory extends BaseCbpsObjectAdapterCopyFactory {

	@Override
	@Inject
	protected void setFactory(@Persistent CbpsObjectAdapterFactory factory) {
		super.setFactory(factory);
	}
}
