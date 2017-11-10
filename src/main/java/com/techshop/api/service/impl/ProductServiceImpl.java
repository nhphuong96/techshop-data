package com.techshop.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.techshop.api.converter.ProductConverter;
import com.techshop.api.converter.ProductSpecificationConverter;
import com.techshop.api.dao.ProductDAO;
import com.techshop.api.dao.ProductSpecificationDAO;
import com.techshop.api.entity.Product;
import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.model.ProductInformation;
import com.techshop.api.model.SpecificationInformation;
import com.techshop.api.service.ProductService;
import com.techshop.api.util.Result;
import com.techshop.api.validator.RequirementValidator;

@Stateless
public class ProductServiceImpl implements ProductService {

	private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@EJB
	private ProductDAO productDao;
	
	@EJB
	private ProductSpecificationDAO productSpecificationDAO;
	
	@EJB
	private RequirementValidator validator;
	
	@EJB
	private ProductConverter productConverter;
	
	@EJB
	private ProductSpecificationConverter productSpecificationConverter;
	
	@Override
	public Result<Product> saveProduct(ProductInformation productInformation) {
		if (productInformation == null) {
			logger.error("Product Information is null");
			return new Result<>(null, "Product Information is null", false);
		}
		boolean isValidObject = validator.validateObjectPropertiesNotNull(productInformation);
		if (isValidObject == false) {
			return new Result<>(null, "Some properties are null", false);
		}
		Result<Product> convertResult = productConverter.convert(productInformation);
		if (convertResult.getSuccess() == false) {
			return convertResult;
		}
		Product product = productConverter.convert(productInformation).getData();
		
		Result<Product> result = productDao.save(product);
		if (result.getSuccess() == false) {
			return new Result<>(null, result.getMessage(), false);
		}
		
		for (SpecificationInformation si : productInformation.getSpecificationInfos()) {
			Result<ProductSpecification> spConvertResult = productSpecificationConverter.convert(si); 
			if (spConvertResult.getSuccess() == false) {
				return new Result<>(null, convertResult.getMessage(), false);
			}
			ProductSpecification productSpecification = spConvertResult.getData();
			productSpecification.setProduct(product);
			Result<ProductSpecification> saveResult = productSpecificationDAO.save(productSpecification);
			if (saveResult.getSuccess() == false) {
				return new Result<>(null, saveResult.getMessage(), false);
			}
		}
		return new Result<Product>(product, "Success", true);
	}

	@Override
	public Result<Long> deleteProduct(Long id) {
		Result<Long> deleteSpecProductResult = productSpecificationDAO.deleteAllByProductId(id);
		if (deleteSpecProductResult.getSuccess() == false) {
			return deleteSpecProductResult;
		}
		Result<Long> deleteResult = productDao.delete(id);
		if (deleteResult.getSuccess() == false) {
			return deleteResult;
		}
		return deleteResult;
	}

	@Override
	public Result<ProductInformation> getProduct(Long productId) {
		Result<Product> getResult = productDao.findOne(productId);
		if (getResult.getSuccess() == false) {
			return new Result<>(null, getResult.getMessage(), false);
		}
		Result<ProductInformation> piConvertResult = productConverter.convert(getResult.getData());
		if (piConvertResult.getSuccess() == false) {
			return new Result<>(null, piConvertResult.getMessage(), false);
		}
		return new Result<ProductInformation>(piConvertResult.getData(), "Success", true);
	}

	@Override
	public Result<List<ProductInformation>> getAllProduct() {
		Result<List<Product>> getAllResult = productDao.findAll();
		if (getAllResult.getSuccess() == false) {
			return new Result<>(null, getAllResult.getMessage(), false);
		}
		List<ProductInformation> productInfos = new ArrayList<>();
		for (Product p : getAllResult.getData()) {
			Result<ProductInformation> eachResult = this.getProduct(p.getId());
			if (eachResult.getSuccess() == false) {
				return new Result<>(null, eachResult.getMessage(), false);
			}
			productInfos.add(eachResult.getData());
		}
		return new Result<List<ProductInformation>>(productInfos, "Success", true);
	}

	@Override
	public Result<Product> updateProduct(Product product) {
		Result<Product> result = productDao.update(product);
		return result;
	}

}
