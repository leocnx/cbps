package top.auok.cbps.model.management;

import java.util.Set;

import top.auok.cbps.model.CbpsObject;

public interface Rate extends CbpsObject {

	String getRateCode();

	void setRateCode(String rateCode);

	String getMemo();

	void setMemo(String memo);

	Set<RateItem> getRateItems();

	void setRateItems(Set<RateItem> rateItems);
}
