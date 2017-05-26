package top.auok.cbps.web.service.model.injection.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import top.auok.cbps.model.base.adapter.CbpsObjectAdapterCopyFactory;

public class GenericJSONDAO<T, K> extends TransparentGenericDAOProxy<T, K> {

	protected CbpsObjectAdapterCopyFactory copyFactory;

	@SuppressWarnings("unchecked")
	protected T jsonAnyCopy(Object source) {
		return jsonCopy((T) source);
	}

	protected T jsonCopy(T source) {
		return copyFactory.createAdapterFor(source);
	}

	@Override
	public T findById(K key, String... fetchProfiles) {
		return jsonCopy(delegate.findById(key, fetchProfiles));
	}

	@Override
	public <C extends Collection<? super T>> C findAll(C receivingList) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final List<T> obtained = delegate.findAll(new ArrayList());
		jsonCopy(receivingList, obtained);
		return receivingList;
	}

	protected void jsonCopy(Collection<? super T> receivingList, Iterable<T> obtained) {
		for (T client : obtained) {
			receivingList.add(jsonCopy(client));
		}
	}
}
