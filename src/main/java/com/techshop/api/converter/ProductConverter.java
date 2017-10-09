package com.techshop.api.converter;

import com.techshop.api.entity.Product;
import com.techshop.api.model.ProductInformation;
import com.techshop.api.util.Result;

public interface ProductConverter {
	Result<Product> convert(ProductInformation pi);
	Result<ProductInformation> convert(Product product);
}
