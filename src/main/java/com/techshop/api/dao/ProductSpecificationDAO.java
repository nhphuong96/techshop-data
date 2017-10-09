package com.techshop.api.dao;

import java.util.List;

import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.util.Result;

public interface ProductSpecificationDAO extends GenericDAO<ProductSpecification, Long> {
	Result<List<ProductSpecification>> findAllByProductId(Long id);
	Result<Long> deleteAllByProductId(Long productId);
}
