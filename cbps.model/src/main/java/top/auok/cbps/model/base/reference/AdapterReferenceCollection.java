package top.auok.cbps.model.base.reference;

import java.util.Collection;

import top.auok.cbps.model.base.adapter.Adapter;

@SuppressWarnings("unchecked")
public abstract class AdapterReferenceCollection<I, BT extends I, TT extends Adapter<? extends BT>, C extends Collection<I>> {

	private C cache;

	protected void cache(C collection) {
		this.cache = collection;
	}

	public void reset() {
		cache = null;
	}

	private BT getBaseObject(TT adapter) {
		return adapter == null ? null : adapter.unwrap();
	}

	private C getBaseCollection(C collection) {
		if (collection == null) {
			return null;
		}
		C result = createCollection();
		for (I typedObject : collection) {
			result.add((I) getBaseObject((TT) typedObject));
		}
		return result;
	}

	public void set(C newCollection) {
		set(newCollection, false);
	}

	public void set(C newCollection, boolean cache) {
		if (newCollection != null) {
			C baseCollection = getBaseCollection(newCollection);
			setReferenceOnDelegate(baseCollection);
			for (I baseOject : baseCollection) {
				setBackReferenceOf((BT) baseOject);
			}
			for (I typedObject : newCollection) {
				if (typedObject != null) {
					setBackReferenceOf((TT) typedObject);
				}
			}
		}
		if (cache) {
			this.cache = newCollection;
		}
	}

	protected abstract void setReferenceOnDelegate(C baseTarget);

	protected void setBackReferenceOf(TT target) {
	}

	protected void setBackReferenceOf(BT target) {
	}

	public C get() {
		if (this.cache == null) {
			C baseCollection = getValueFromDelegate();
			if (baseCollection != null) {
				this.cache = createCollection();
				for (I baseObject : baseCollection) {
					this.cache.add(createAdapterFor((BT) baseObject));
				}
			}
		}
		return this.cache;
	}

	public void pushCacheDown(boolean keepCache) {
		set(this.cache, keepCache);
	}

	protected abstract C getValueFromDelegate();

	protected abstract I createAdapterFor(BT baseObject);

	protected abstract C createCollection();
}
