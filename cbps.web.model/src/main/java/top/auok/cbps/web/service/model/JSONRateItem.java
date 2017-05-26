package top.auok.cbps.web.service.model;

import javax.enterprise.context.Dependent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import top.auok.cbps.model.base.BaseRate;
import top.auok.cbps.model.base.BaseRateItem;
import top.auok.cbps.model.base.adapter.CbpsObjectAdapterFactory.RateItemAdapter;
import top.auok.cbps.model.base.reference.rate.RateItemToRateReference;
import top.auok.cbps.model.management.Rate;

@JsonSerialize(as = JSONRateItem.class)
@Dependent
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "id" })
public class JSONRateItem extends JSONBaseAdapter<BaseRateItem> implements RateItemAdapter {

	private JSONRateItemToRateReference rate;

	JSONRateItem(BaseRateItem delegate) {
		super(delegate);
		rate = new JSONRateItemToRateReference();
	}

	public JSONRateItem() {
		this(new BaseRateItem());
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
	@JsonBackReference
	public Rate getRate() {
		return rate.get();
	}

	@Override
	@JsonDeserialize(as = JSONRate.class)
	public void setRate(Rate baseRate) {
		rate.set(baseRate);
	}

	private class JSONRateItemToRateReference extends RateItemToRateReference<JSONRate> {
		@Override
		protected BaseRateItem getBaseHolder() {
			return delegate;
		}

		@Override
		protected JSONRate createAdapterFor(BaseRate baseObject) {
			return new JSONRate(baseObject);
		}
	}

}
