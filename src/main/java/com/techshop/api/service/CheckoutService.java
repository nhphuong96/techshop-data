package com.techshop.api.service;

import com.techshop.api.model.CheckoutInformation;
import com.techshop.api.util.EntityResult;

public interface CheckoutService {
	public EntityResult save(CheckoutInformation model);
}
