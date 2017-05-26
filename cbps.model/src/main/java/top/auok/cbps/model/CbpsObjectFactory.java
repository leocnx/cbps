package top.auok.cbps.model;

import top.auok.cbps.model.management.Rate;
import top.auok.cbps.model.management.RateItem;

public interface CbpsObjectFactory {

	Rate createRate();

	RateItem createRateItem();
}
