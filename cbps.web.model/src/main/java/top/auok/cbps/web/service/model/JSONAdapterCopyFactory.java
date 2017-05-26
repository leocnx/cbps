package top.auok.cbps.web.service.model;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import top.auok.cbps.model.base.adapter.BaseCbpsObjectAdapterCopyFactory;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory;

@Dependent
public class JSONAdapterCopyFactory extends BaseCbpsObjectAdapterCopyFactory {

	@Override
	@Inject
	protected void setFactory(CbpsObjectAdapterFactory factory) {
		super.setFactory(factory);
	}
}
