package com.techshop.api.converter;

import java.util.List;

import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.model.SpecificationInformation;
import com.techshop.api.util.Result;

public interface ProductSpecificationConverter {

	public Result<ProductSpecification> convert(SpecificationInformation specificationInformation);
	public Result<List<SpecificationInformation>> convert(List<ProductSpecification> productSpecifications);
}
