package top.auok.cbps.model.base.adapter;

import top.auok.cbps.model.CbpsObjectFactory;

public interface CbpsObjectAdapterCopyFactory extends CbpsObjectFactory {

	<T> T createAdapterFor(T adaptee);

	CbpsObjectAdapterCopyFactory setAdaptee(Object adaptee);
}
