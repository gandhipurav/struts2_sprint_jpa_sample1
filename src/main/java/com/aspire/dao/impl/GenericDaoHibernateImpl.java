package com.aspire.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.dao.GenericDao;

public class GenericDaoHibernateImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK>, UserDetailsService {

	private Logger log = Logger.getLogger(GenericDaoHibernateImpl.class
			.getName());

	private Class<T> type;

	public GenericDaoHibernateImpl() {

	}

	public Class<T> getType() {
		return type;
	}

	public GenericDaoHibernateImpl(Class<T> type) {
		this.type = type;
	}

	/*
	 * Create the record
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.dao.GenericDao#create(java.lang.Object)
	 */
	@Transactional
	@Override
	public T create(T newInstance) throws Exception {
		log.info("Executing method : create()");
		getSessionFactory().getCurrentSession().save(newInstance);
		return newInstance;
	}

	/*
	 * Return the record of the id
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.dao.GenericDao#findById(java.io.Serializable)
	 */

	@SuppressWarnings("unchecked")
	@Override
	public T findById(PK id) throws Exception {
		log.info("Executing method : findById()");
		return (T) getSessionFactory().getCurrentSession().get(type, id);
	}

	/*
	 * Update the record
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.dao.GenericDao#update(java.lang.Object)
	 */
	@Transactional
	@Override
	public T update(T transientObject) throws Exception {
		log.info("Executing method : update()");
		getSessionFactory().getCurrentSession().update(transientObject);
		return transientObject;
	}

	/*
	 * Delete the record
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.dao.GenericDao#delete(java.lang.Object)
	 */
	@Transactional
	@Override
	public void delete(T persistentObject) throws Exception {
		log.info("Executing method : delete()");
		getSessionFactory().getCurrentSession().delete(persistentObject);
	}

	/*
	 * Get all the records
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.dao.GenericDao#findAll()
	 */
	@Override
	public List<T> findAll() throws Exception {
		log.info("Executing method : findAll()");
		return getHibernateTemplate().loadAll(this.type);
	}

	/**
	 * Get the unique record according to parameters
	 * 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 * @throws Exception
	 */
	public Object findUniqueByParams(String queryName,
			Map<String, Object> queryParameters) throws Exception {
		log.info("Executing method : findUniqueByParams()");
		Query query = createdNamedQuery(queryName);
		query.setCacheable(true);
		setParametersInQuery(query, queryParameters);
		return query.uniqueResult();
	}

	/**
	 * Get the records according to parameters and return the number of result
	 * equals to maxResults
	 * 
	 * @param queryName
	 * @param queryParameters
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public List<T> findByQueryParams(String queryName,
			Map<String, Object> queryParameters, int maxResults)
			throws Exception {
		log.info("Executing method : findByQueryParams()");
		Query query = createdNamedQuery(queryName);
		setParametersInQuery(query, queryParameters);
		query.setMaxResults(maxResults);
		return runQuery(query);
	}

	/**
	 * Get the object according to query parameters
	 * 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 * @throws Exception
	 */
	public List<T> findByQueryParams(String queryName,
			Map<String, Object> queryParameters) throws Exception {
		log.info("Executing method : findByQueryParams()");
		Query query = createdNamedQuery(queryName);
		setParametersInQuery(query, queryParameters);
		return runQuery(query);
	}

	/**
	 * Get the data according to Criteria
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		log.info("Executing method : findByCriteria()");
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * Return the hibernate query
	 * 
	 * @param queryName
	 * @return
	 * @throws Exception
	 */
	private Query createdNamedQuery(String queryName) throws Exception {
		log.info("Executing method : createdNamedQuery()");
		Query query = getSession().getNamedQuery(queryName);
		query.setCacheable(true);
		return query;
	}

	/**
	 * Set the parameter into the query
	 * 
	 * @param query
	 * @param queryParameters
	 * @throws Exception
	 */
	private static void setParametersInQuery(Query query,
			Map<String, Object> queryParameters) throws Exception {
		if (queryParameters != null) {
			Iterator<Map.Entry<String, Object>> queryParamIterator = queryParameters
					.entrySet().iterator();
			while (queryParamIterator.hasNext()) {
				Map.Entry<String, Object> entry = queryParamIterator.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * Runs the query and return result object list
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<T> runQuery(Query query) throws Exception {
		query.setCacheable(true);
		return (List<T>) query.list();
	}

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.type);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("Executing method : loadUserByUsername() with username = "
				+ username);
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("username", username));
		com.aspire.model.User user = (com.aspire.model.User) (getHibernateTemplate()
				.findByCriteria(criteria).get(0));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword().toLowerCase(),
				true, true, true, true, authorities);
	}
}