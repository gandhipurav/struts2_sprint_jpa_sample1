package com.aspire.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
	/** Persist the newInstance object into database */
	T create(T newInstance) throws Exception;

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	T findById(PK id) throws Exception;

	/** Save changes made to a persistent object. */
	T update(T transientObject) throws Exception;

	/** Remove an object from persistent storage in the database */
	void delete(T persistentObject) throws Exception;

	public List<T> findAll() throws Exception;
}
