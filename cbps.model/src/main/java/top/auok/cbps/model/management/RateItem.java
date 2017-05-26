package top.auok.cbps.model.management;

import top.auok.cbps.model.CbpsObject;

public interface RateItem extends CbpsObject {

	Rate getRate();

	void setRate(Rate baseRate);
}
