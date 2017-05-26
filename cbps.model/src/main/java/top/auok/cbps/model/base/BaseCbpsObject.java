package top.auok.cbps.model.base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import java.util.Date;

import top.auok.cbps.model.CbpsObject;

public abstract class BaseCbpsObject implements CbpsObject {

	public static final ObjectByDateComparator BY_DATE_COMPARATOR = new ObjectByDateComparator();

	public static class ObjectByDateComparator implements Comparator<CbpsObject> {

		@Override
		public int compare(CbpsObject o1, CbpsObject o2) {
			return o1.getCreationDate().compareTo(o2.getCreationDate());
		}

	}

	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	private Long id;

	private Date creationDate;

	private Long version;

	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Date getUpdateDate() {
		return updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CbpsObject))
			return false;
		CbpsObject other = (CbpsObject) obj;
		final Long otherId = other.getId();
		if (id != null && otherId != null && !id.equals(otherId))
			return false;
		return true;
	}

}
