package top.auok.cbps.persistence;

import java.util.List;

import top.auok.cbps.model.management.Rate;
import top.auok.persistence.DataAccessObject;

public interface RateDAO extends DataAccessObject<Rate, Long> {

	long findByParameter(List<Rate> receivingList, String id, String rateCode, int startIndex, int resultsNumber);

}
