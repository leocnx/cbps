package top.auok.cbps.persistence.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import top.auok.cbps.model.base.BaseCbpsObject;
import top.auok.cbps.model.base.adapter.AbstractBaseAdapter;

@MappedSuperclass
public abstract class PersistentBaseAdapter<T extends BaseCbpsObject> extends AbstractBaseAdapter<T> {

	protected PersistentBaseAdapter() {
		this(null);
	}

	protected PersistentBaseAdapter(T delegate) {
		super(delegate);
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return delegate.getId();
	}

	@Override
	public void setId(Long id) {
		delegate.setId(id);
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	public Date getCreationDate() {
		return super.getCreationDate();
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	public Date getUpdateDate() {
		return super.getUpdateDate();
	}

	@Override
	@Version
	public Long getVersion() {
		return super.getVersion();
	}
}
