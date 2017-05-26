package top.auok.cbps.model.base.adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import top.auok.cbps.model.CbpsObject;
import top.auok.cbps.model.base.BaseCbpsObject;

public abstract class AbstractBaseAdapter<T extends BaseCbpsObject> implements Adapter<T>, CbpsObject, PropertyChangeListener {

	protected T delegate;

	protected AbstractBaseAdapter(T delegate) {
		adapt(delegate);
	}

	@Override
	public void adapt(T adapted) {
		if (this.delegate != null) {
			this.delegate.removePropertyChangeListener(this);
		}
		this.delegate = adapted;
		this.delegate.addPropertyChangeListener(this);
	}

	@Override
	public T unwrap() {
		return delegate;
	}

	@Override
	public Long getId() {
		return delegate.getId();
	}

	@Override
	public void setId(Long id) {
		delegate.setId(id);
	}

	@Override
	public Date getCreationDate() {
		return delegate.getCreationDate();
	}

	@Override
	public void setCreationDate(Date creationDate) {
		delegate.setCreationDate(creationDate);
	}

	@Override
	public Date getUpdateDate() {
		return delegate.getUpdateDate();
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		delegate.setUpdateDate(updateDate);
	}

	@Override
	public Long getVersion() {
		return delegate.getVersion();
	}

	@Override
	public void setVersion(Long version) {
		delegate.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (delegate == null) {
			return super.equals(obj);
		}
		return delegate.equals(obj);
	}

	@Override
	public int hashCode() {
		if (delegate == null) {
			return super.hashCode();
		}
		return delegate.hashCode();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// do nothing
	}

}
