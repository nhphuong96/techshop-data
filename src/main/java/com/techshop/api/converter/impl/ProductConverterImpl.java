package com.techshop.api.converter.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.techshop.api.converter.ProductConverter;
import com.techshop.api.converter.ProductSpecificationConverter;
import com.techshop.api.dao.CategoryDAO;
import com.techshop.api.dao.ManufacturerDAO;
import com.techshop.api.dao.ProductDAO;
import com.techshop.api.dao.ProductSpecificationDAO;
import com.techshop.api.entity.Category;
import com.techshop.api.entity.Manufacturer;
import com.techshop.api.entity.Product;
import com.techshop.api.entity.ProductSpecification;
import com.techshop.api.model.ProductInformation;
import com.techshop.api.model.SpecificationInformation;
import com.techshop.api.util.Result;

@Stateless
public class ProductConverterImpl implements ProductConverter {
	
	@EJB
	private CategoryDAO categoryDao;
	
	@EJB
	private ProductDAO productDao;
	
	@EJB
	private ManufacturerDAO manufacturerDao;
	
	@EJB
	private ProductSpecificationDAO productSpecificationDao;
	
	@EJB
	private ProductSpecificationConverter productSpecificationConverter;
	
	@Override
	public Result<Product> convert(ProductInformation productInformation) {
		Product product = new Product();
		product.setName(productInformation.getProductName());
		product.setAlias(productInformation.getAlias());
		Category category = categoryDao.findOne(productInformation.getCategoryId()).getData();
		product.setCategory(category);
		product.setDescriptionHtml(productInformation.getDescriptionHTML());
		product.setImage1(productInformation.getImage1());
		product.setImage2(productInformation.getImage2());
		product.setImage3(productInformation.getImage3());
		product.setPrice(productInformation.getPrice());
		Manufacturer manufacturer = manufacturerDao.findOne(productInformation.getManufacturerId()).getData();
		product.setManufacturer(manufacturer);
		return new Result<>(product, "Success", true);
	}

	@Override
	public Result<ProductInformation> convert(Product product) {
		ProductInformation pi = new ProductInformation();
		pi.setAlias(product.getAlias());
		pi.setId(product.getId());
		pi.setProductName(product.getName());
		pi.setImage1(product.getImage1());
		pi.setImage2(product.getImage2());
		pi.setImage3(product.getImage3());
		pi.setDescriptionHTML(product.getDescriptionHtml());
		pi.setPrice(product.getPrice());
		pi.setManufacturerId(product.getManufacturer().getId());
		pi.setCategoryId(product.getCategory().getId());
		Result<List<ProductSpecification>> psGetResult = productSpecificationDao.findAllByProductId(product.getId());
		if (psGetResult.getSuccess() == false) {
			return new Result<>(null, psGetResult.getMessage(), false);
		}
		Result<List<SpecificationInformation>> siConvertResult = productSpecificationConverter.convert(psGetResult.getData());
		pi.setSpecificationInfos(siConvertResult.getData());
		return new Result<>(pi, "Success", true);
		
	}
}
