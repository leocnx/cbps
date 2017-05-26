package top.auok.cbps.web.service.model.injection.dao;

import java.util.Collection;
import java.util.Set;

import top.auok.persistence.DataAccessObject;

public class TransparentGenericDAOProxy<T, K> implements DataAccessObject<T, K> {

	protected DataAccessObject<T, K> delegate;

	@Override
	public void create(T entity) {
		delegate.create(entity);
	}

	@Override
	public T findById(K key, String... fetchProfiles) {
		return delegate.findById(key, fetchProfiles);
	}

	@Override
	public <C extends Collection<? super T>> C findAll(C receivingList) {
		return delegate.findAll(receivingList);
	}

	@Override
	public T update(T entity) {
		return delegate.update(entity);
	}

	@Override
	public void delete(K key) {
		delegate.delete(key);
	}

	@Override
	public <C extends Collection<? super T>> C findByIds(Set<K> keys, C receivingList) {
		return delegate.findByIds(keys, receivingList);
	}
}
