package top.auok.cbps.model.base.adapter;

public interface Adapter<T> {

	void adapt(T adapted);

	T unwrap();
}
