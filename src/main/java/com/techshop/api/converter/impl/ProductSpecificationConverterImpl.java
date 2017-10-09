package com.techshop.api.converter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.techshop.api.converter.ProductSpecificationConverter;
import com.techshop.api.dao.SpecificationTypeDAO;
import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.entity.SpecificationType;
import com.techshop.api.model.SpecificationInformation;
import com.techshop.api.util.Result;

@Stateless
public  class ProductSpecificationConverterImpl implements ProductSpecificationConverter {

	@EJB
	private  SpecificationTypeDAO specTypeDao;
	
	@Override
	public Result<ProductSpecification> convert(SpecificationInformation specificationInformation) {
		if (specificationInformation == null) {
			return new Result<>(null, "Specification Information is null", false);
		}
		
		SpecificationType specType = specTypeDao.findOne(specificationInformation.getId()).getData();
		if (specType == null) {
			return new Result<>(null, "Specification Type not found", false);
		}
		
		ProductSpecification productSpecification = new ProductSpecification();
		productSpecification.setSpecificationType(specType);
		productSpecification.setSpecificationValue(specificationInformation.getValue());
		return new Result<ProductSpecification>(productSpecification, "Convert ProductSpecification complete", true);
	}

	@Override
	public Result<List<SpecificationInformation>> convert(List<ProductSpecification> productSpecifications) {
		List<SpecificationInformation> specificationInfors = new ArrayList<>();
		for (ProductSpecification pi : productSpecifications) {
			SpecificationInformation si = new SpecificationInformation();
			si.setId(pi.getSpecificationType().getId());
			si.setValue(pi.getSpecificationValue());
			specificationInfors.add(si);
		}
		return new Result<List<SpecificationInformation>>(specificationInfors, "Success", true);
	}
}
