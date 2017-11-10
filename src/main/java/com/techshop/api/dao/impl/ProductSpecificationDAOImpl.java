package com.techshop.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.ProductSpecificationDAO;
import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.util.Result;

@Stateless
public class ProductSpecificationDAOImpl extends GenericDAOImpl<ProductSpecification, Long> implements ProductSpecificationDAO, GenericDAO<ProductSpecification, Long>  {

	private final static Logger logger = Logger.getLogger(ProductSpecificationDAOImpl.class);

	@PersistenceContext(name = "techshop-data")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Result<List<ProductSpecification>> findAllByProductId(Long id) {
		Query query = entityManager.createQuery("Select a FROM ProductSpecification a WHERE a.product = " + id);
		logger.debug("Delete in table ProductSpecification with product id is " + id);
		List<ProductSpecification> resultList = new ArrayList<>();
		try {
			resultList = query.getResultList();
		}
		catch (PersistenceException e) {
			logger.error("Persistence Exception", e);
		}
		return new Result<List<ProductSpecification>>(resultList, "Find " + resultList.size() + " record(s).", true);
	}

	@Override
	public Result<Long> deleteAllByProductId(Long productId) {
		Query query = entityManager.createQuery("DELETE FROM ProductSpecification a WHERE a.product = " + productId);
		logger.debug("Delete in table ProductSpecification with product id is " + productId);
		try {
			int numsOfRecord = query.executeUpdate();
			return new Result<Long>(new Long(numsOfRecord), numsOfRecord + " affected.", true);
		}
		catch (PersistenceException e) {
			logger.error("Persistence Exception", e);
		}
		return null;
	}

}
