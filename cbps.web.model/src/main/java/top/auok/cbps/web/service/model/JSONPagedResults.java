package top.auok.cbps.web.service.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JSONPagedResults<T> {

	static {
		final Map<String, Function<JSONPagedResults<?>, Object>> map = new HashMap<>();
		map.put("pageNumber", JSONPagedResults::getPageNumber);
		map.put("page", JSONPagedResults::getPage);
	}

	private Long totalNumberOfResults;

	private Integer pageNumber;

	private Integer pageSize;

	private Collection<? extends T> page;

	@SuppressWarnings("unchecked")
	public JSONPagedResults(Long totalNumberOfResults, Integer pageNumber, Integer pageSize,
			Collection<? super T> page) {
		this.totalNumberOfResults = totalNumberOfResults;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.page = (Collection<? extends T>) page;
	}

	public Long getTotalNumberOfResults() {
		return totalNumberOfResults;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Collection<? extends T> getPage() {
		return page;
	}

}
