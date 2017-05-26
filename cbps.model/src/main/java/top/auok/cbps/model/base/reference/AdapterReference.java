package top.auok.cbps.model.base.reference;

import top.auok.cbps.model.base.adapter.Adapter;

public abstract class AdapterReference<BT, TT extends Adapter<? extends BT>> {

	private TT cache;

	private BT getBaseObject(TT adapter) {
		return adapter == null ? null : adapter.unwrap();
	}

	public void set(Object o) {
		set(o, false);
	}

	public void set(Object o, boolean cache) {
		@SuppressWarnings("unchecked")
		TT typedObject = (TT) o;
		setReferenceOnDelegate(getBaseObject(typedObject));
		if (typedObject != null) {
			setBackReferenceOf(typedObject);
			if (cache) {
				this.cache = typedObject;
			}
		}
	}

	protected abstract void setReferenceOnDelegate(BT baseTarget);

	protected void setBackReferenceOf(TT target) {
	}

	public TT get() {
		if (this.cache == null) {
			BT baseObject = getValueFromDelegate();
			if (baseObject != null) {
				this.cache = createAdapterFor(baseObject);
			}
		}
		return this.cache;
	}

	public void reset() {
		cache = null;
	}

	protected abstract BT getValueFromDelegate();

	protected abstract TT createAdapterFor(BT baseObject);
}
