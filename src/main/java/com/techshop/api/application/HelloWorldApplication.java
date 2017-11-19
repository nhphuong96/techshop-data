package com.techshop.api.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.techshop.api.controller.CategoryController;
import com.techshop.api.controller.CheckoutController;
import com.techshop.api.controller.CustomerController;
import com.techshop.api.controller.ManufacturerController;
import com.techshop.api.controller.OrderController;
import com.techshop.api.controller.OrderDetailController;
import com.techshop.api.controller.PaymentController;
import com.techshop.api.controller.ProductController;
import com.techshop.api.controller.SpecificationTypeController;

@ApplicationPath("/*")
public class HelloWorldApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(CategoryController.class);
		s.add(ProductController.class);
		s.add(SpecificationTypeController.class);
		s.add(ManufacturerController.class);
		s.add(CustomerController.class);
		s.add(OrderDetailController.class);
		s.add(OrderController.class);
		s.add(PaymentController.class);
		s.add(CheckoutController.class);
		return s;
	}
}
