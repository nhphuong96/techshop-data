package com.techshop.api.service;

import com.techshop.api.entity.Order;
import com.techshop.api.model.CheckoutInformation;
import com.techshop.api.util.Result;

public interface CheckoutService {
	public Result<Order> save(CheckoutInformation model);
}
