package com.techshop.api.service;

import java.util.List;

import com.techshop.api.entity.Product;
import com.techshop.api.model.ProductInformation;
import com.techshop.api.util.Result;

public interface ProductService {
	Result<Product> saveProduct(ProductInformation productInformation);
	Result<Long> deleteProduct(Long id);
	Result<ProductInformation> getProduct(Long id);
	Result<List<ProductInformation>> getAllProduct();
}
