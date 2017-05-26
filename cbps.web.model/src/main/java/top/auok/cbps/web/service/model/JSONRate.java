package top.auok.cbps.web.service.model;

import java.beans.PropertyChangeEvent;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateAdapter;
import top.auok.cbps.model.base.reference.rate.RateToRateItemReference;
import top.auok.cbps.model.management.RateItem;

@JsonSerialize(as = JSONRate.class)
@Dependent
@JsonInclude(Include.NON_EMPTY)
public class JSONRate extends JSONBaseAdapter<BaseRate> implements RateAdapter {

	private JSONRateToRateItemReference rateItems;

	JSONRate(BaseRate delegate) {
		super(delegate);
		rateItems = new JSONRateToRateItemReference();
	}

	public JSONRate() {
		this(new BaseRate());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case BaseRate.RATE_ITEMS:
			this.rateItems.reset();
			break;
		default:
			break;
		}
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
	public String getRateCode() {
		return delegate.getRateCode();
	}

	@Override
	public void setRateCode(String rateCode) {
		delegate.setRateCode(rateCode);
	}

	@Override
	public String getMemo() {
		return delegate.getMemo();
	}

	@Override
	public void setMemo(String memo) {
		delegate.setMemo(memo);
	}

	@Override
	@Valid
	@JsonSerialize(contentAs = JSONRateItem.class)
	public Set<RateItem> getRateItems() {
		return rateItems.get();
	}

	@Override
	@JsonDeserialize(contentAs = JSONRateItem.class)
	public void setRateItems(Set<RateItem> rateItems) {
		this.rateItems.set(rateItems, true);
	}

	private class JSONRateToRateItemReference extends RateToRateItemReference<JSONRateItem> {
		@Override
		protected BaseRate getBaseHolder() {
			return delegate;
		}

		@Override
		protected JSONRateItem createAdapterFor(BaseRateItem baseObject) {
			return new JSONRateItem(baseObject);
		}

	}

}
