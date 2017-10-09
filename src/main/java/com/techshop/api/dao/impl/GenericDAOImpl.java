package com.techshop.api.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.util.Result;

@Stateless
public class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K> {

	private final static Logger logger = Logger.getLogger(GenericDAOImpl.class);

	private Class<T> entityClass;

	@PersistenceContext(name = "techshop-data")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public Result<T> save(T t) {
		try {
			this.entityManager.persist(t);
			return new Result<T>(t);
		} catch (EntityExistsException e) {
			logger.error("Object have already existed", e);
			return new Result<>(null, "Object have already existed", false);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid object argument", e);
			return new Result<>(null, "Invalid object argument", false);
		}
	}

	@Override
	public Result<T> findOne(K id) {
		try {
			T t = this.entityManager.find(entityClass, id);
			if (t == null) {
				logger.info("Object not found");
				return new Result<T>(null, "Object not found", false);
			}
			return new Result<T>(t);

		} catch (IllegalArgumentException e) {
			logger.error("Invalid object argument", e);
			return null;
		}

	}

	@Override
	public Result<T> update(T t) {
		try {
			this.entityManager.merge(t);
			return new Result<T>(t, "Success", true);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid object argument", e);
			return new Result<>(null, "Invalid object argument", false);
		}

	}

	@Override
	public Result<K> delete(K id) {
		try {
			T t = entityManager.find(entityClass, id);
			this.entityManager.remove(t);
			return new Result<K>(id, "Success", true);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid object argument", e);
			return new Result<K>(null, "Invalid object argument", false);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Result<List<T>> findAll() {
		try {
			Query query = entityManager.createQuery("select a from " + entityClass.getSimpleName() + " a");
			return new Result<List<T>>(query.getResultList(), "Success", true);
		} 
		catch (PersistenceException e) {
			logger.error("Query timeout execution", e);
			return new Result<List<T>>(null, "Query time out", false);
		}
		
	}

}
