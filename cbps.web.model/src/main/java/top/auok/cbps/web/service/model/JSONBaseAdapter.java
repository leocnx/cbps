package top.auok.cbps.web.service.model;

import java.util.Date;

import top.auok.cbps.model.base.BaseCbpsObject;
import top.auok.cbps.model.base.adapter.AbstractBaseAdapter;

public class JSONBaseAdapter<T extends BaseCbpsObject> extends AbstractBaseAdapter<T> {

	private Class<?> view;

	protected JSONBaseAdapter(T delegate) {
		super(delegate);
	}

	public void setView(Class<?> view) {
		this.view = view;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public Date getCreationDate() {
		return super.getCreationDate();
	}

	@Override
	public Date getUpdateDate() {
		return super.getUpdateDate();
	}

	@Override
	public Long getVersion() {
		return super.getVersion();
	}

}
