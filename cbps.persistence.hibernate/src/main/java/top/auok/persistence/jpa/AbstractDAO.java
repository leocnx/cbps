package top.auok.persistence.jpa;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.Session;

import top.auok.persistence.DataAccessObject;

public class AbstractDAO<T, ET extends T, K> implements DataAccessObject<T, K> {

	protected Class<ET> entityClass;

	protected AbstractDAO(Class<ET> entityClass) {
		this.entityClass = entityClass;
	}

	@PersistenceContext
	protected EntityManager em;

	public void create(T entity) {
		em.persist(entity);
	}

	protected void enableFetchProfiles(String[] fetchProfiles) {
		if (fetchProfiles.length > 0) {
			Session session = em.unwrap(Session.class);
			for (String fetchProfile : fetchProfiles) {
				session.enableFetchProfile(fetchProfile);
			}
		}
	}

	public T findById(K key, String... fetchProfiles) {
		enableFetchProfiles(fetchProfiles);
		return em.find(entityClass, key);
	}

	@Override
	public <C extends Collection<? super T>> C findByIds(Set<K> keys, C receivingList) {

		final CriteriaBuilder criteriabuilder = em.getCriteriaBuilder();
		final CriteriaQuery<ET> query = criteriabuilder.createQuery(entityClass);

		Root<ET> entities = query.from(entityClass);
		receivingList.addAll(
				em.createQuery(query.select(entities).where(criteriabuilder.in(entities.<Set<K>>get("id")).value(keys)))
						.getResultList());
		return receivingList;
	}

	public <C extends Collection<? super T>> C findAll(C receivingList) {
		final CriteriaQuery<ET> query = em.getCriteriaBuilder().createQuery(entityClass);
		receivingList.addAll(em.createQuery(query.select(query.from(entityClass))).getResultList());
		return receivingList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Supplier<Join<?, ?>>> mergeFrom(Root<ET> root,
			Function<Root<ET>, Map<String, Supplier<Join<?, ?>>>> joinProvider,
			Function<Root<ET>, Map<String, Supplier<Fetch<?, ?>>>> fetchSupplier) {
		final Map<String, Supplier<Join<?, ?>>> allJoins = new HashMap<>();
		Optional.ofNullable(joinProvider.apply(root)).ifPresent(allJoins::putAll);
		if (fetchSupplier != null) {
			Optional.ofNullable(fetchSupplier.apply(root)).ifPresent(m -> allJoins.putAll((Map) m));
		}
		return allJoins;
	}

	private long doFindAllWhere(Collection<? super T> receivingList,
			Function<Root<ET>, Map<String, Supplier<Join<?, ?>>>> joinProvider,
			Function<Root<ET>, Map<String, Supplier<Fetch<?, ?>>>> fetchSupplier,
			BiFunction<CriteriaBuilder, Map<String, Join<?, ?>>, Predicate> expressionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Order> orderProvider, int startIndex, int resultsNumber) {
		final CriteriaBuilder criteriabuilder = em.getCriteriaBuilder();
		CriteriaQuery<ET> query = criteriabuilder.createQuery(entityClass);
		CriteriaQuery<Long> countQuery = criteriabuilder.createQuery(Long.class);

		final Root<ET> root = query.from(entityClass);
		final Root<ET> rootCount = countQuery.from(entityClass);

		final Map<String, Supplier<Join<?, ?>>> allJoinSuppliers = mergeFrom(root, joinProvider, fetchSupplier);
		final Map<String, Supplier<Join<?, ?>>> allJoinCountSuppliers = mergeFrom(rootCount, joinProvider, null);

		final Map<String, Join<?, ?>> allJoins = new HashMap<>();
		allJoinSuppliers.forEach((key, value) -> allJoins.put(key, value.get()));

		final Map<String, Join<?, ?>> allJoinCount = new HashMap<>();
		allJoinCountSuppliers.forEach((key, value) -> allJoinCount.put(key, value.get()));

		query.select(root);
		countQuery.select(criteriabuilder.count(rootCount));

		if (expressionProvider != null) {
			query.where(expressionProvider.apply(criteriabuilder, allJoins));
			countQuery.where(expressionProvider.apply(criteriabuilder, allJoinCount));
		}

		if (orderProvider != null) {
			query.orderBy(orderProvider.apply(criteriabuilder, root));
		}

		receivingList
				.addAll(em.createQuery(query).setFirstResult(startIndex).setMaxResults(resultsNumber).getResultList());
		return em.createQuery(countQuery).getSingleResult();
	}

	protected <J, X> long findAllWhere(Collection<? super T> receivingList,
			Function<Root<ET>, Map<String, Supplier<Join<?, ?>>>> joinProvider,
			BiFunction<CriteriaBuilder, Map<String, Join<?, ?>>, Predicate> expressionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Order> orderProvider, int startIndex, int resultsNumber) {

		return doFindAllWhere(receivingList, joinProvider, null, expressionProvider, orderProvider, startIndex,
				resultsNumber);
	}

	protected long findAllWhereWithFetch(Collection<? super T> receivingList,
			Function<Root<ET>, Map<String, Supplier<Join<?, ?>>>> joinProvider,
			Function<Root<ET>, Map<String, Supplier<Fetch<?, ?>>>> fetchSupplier,
			BiFunction<CriteriaBuilder, Map<String, Join<?, ?>>, Predicate> expressionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Order> orderProvider, int startIndex, int resultsNumber) {

		return doFindAllWhere(receivingList, joinProvider, fetchSupplier, expressionProvider, orderProvider, startIndex,
				resultsNumber);
	}

	protected long findAllWhere(Collection<? super T> receivingList,
			BiFunction<CriteriaBuilder, Root<ET>, Expression<Boolean>> expressionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Order> orderProvider, int startIndex, int resultsNumber) {

		return findAllWhereWithProjection(receivingList, null, expressionProvider, orderProvider, startIndex,
				resultsNumber);
	}

	protected long findAllWhereWithProjection(Collection<? super T> receivingList,
			BiFunction<CriteriaBuilder, Root<ET>, List<Selection<?>>> selectionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Expression<Boolean>> expressionProvider,
			BiFunction<CriteriaBuilder, Root<ET>, Order> orderProvider, int startIndex, int resultsNumber) {

		final CriteriaBuilder criteriabuilder = em.getCriteriaBuilder();
		CriteriaQuery<ET> query = criteriabuilder.createQuery(entityClass);

		CriteriaQuery<Long> countQuery = criteriabuilder.createQuery(Long.class);

		Root<ET> root = query.from(entityClass);
		Root<ET> rootCount = countQuery.from(entityClass);

		if (selectionProvider != null) {
			query.multiselect(selectionProvider.apply(criteriabuilder, root));
		} else {
			query.select(root);
		}
		countQuery.select(criteriabuilder.count(rootCount));

		if (expressionProvider != null) {
			query.where(expressionProvider.apply(criteriabuilder, root));
			countQuery.where(expressionProvider.apply(criteriabuilder, rootCount));
		}

		if (orderProvider != null) {
			query.orderBy(orderProvider.apply(criteriabuilder, root));
		}

		receivingList
				.addAll(em.createQuery(query).setFirstResult(startIndex).setMaxResults(resultsNumber).getResultList());
		return em.createQuery(countQuery).getSingleResult();
	}

	protected long findAllWhere(Collection<T> receivingList,
			BiFunction<CriteriaBuilder, Root<ET>, Expression<Boolean>> expressionProvider, int startIndex,
			int resultsNumber) {
		return findAllWhere(receivingList, expressionProvider, null, startIndex, resultsNumber);
	}

	protected T findWithWhere(BiFunction<CriteriaBuilder, Root<ET>, Expression<Boolean>> expressionProvider) {
		final CriteriaBuilder criteriabuilder = em.getCriteriaBuilder();
		CriteriaQuery<ET> query = criteriabuilder.createQuery(entityClass);
		Root<ET> root = query.from(entityClass);
		query = query.select(root);
		if (expressionProvider != null) {
			query = query.where(expressionProvider.apply(criteriabuilder, root));
		}
		List<ET> list = em.createQuery(query).setMaxResults(1).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public void delete(K key) {
		T toRemove = findById(key);
		em.remove(toRemove);
	}

}
