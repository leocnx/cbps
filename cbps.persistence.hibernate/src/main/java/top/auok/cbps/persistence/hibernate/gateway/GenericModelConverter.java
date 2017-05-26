package top.auok.cbps.persistence.hibernate.gateway;

import top.auok.cbps.model.CbpsObject;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;
import top.auok.persistence.DataAccessObject;

public abstract class GenericModelConverter<T extends CbpsObject, K> implements DataAccessObject<T, K> {

	protected Class<? extends T> entityClass;

	protected DataAccessObject<T, K> delegate;

	protected CbpsObjectAdapterCopyFactory factory;

	private T newEntityMergedFrom(T provided) {
		return factory.createAdapterFor(provided);
	}

	private T checked(T provided) {
		return entityClass.isInstance(provided) ? provided : newEntityMergedFrom(provided);
	}

	@Override
	public void create(T provided) {
		final T entity = checked(provided);
		delegate.create(entity);
	}

	@Override
	public T update(T provided) {
		final T entity = checked(provided);
		return delegate.update(entity);
	}
}
