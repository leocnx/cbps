package top.auok.cbps.service.rate;

import java.util.List;

import top.auok.cbps.model.management.Rate;

public interface RateService {

	Long createRate(Rate rate) throws InvalidRateException;

	Long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex, int resultsNumber)
			throws InvalidRateException;

}
