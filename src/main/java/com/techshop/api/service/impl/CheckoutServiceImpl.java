package com.techshop.api.service.impl;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.techshop.api.dao.CustomerDAO;
import com.techshop.api.dao.OrderDAO;
import com.techshop.api.dao.OrderDetailDAO;
import com.techshop.api.dao.PaymentDAO;
import com.techshop.api.dao.ProductDAO;
import com.techshop.api.entity.Customer;
import com.techshop.api.entity.Order;
import com.techshop.api.entity.OrderDetail;
import com.techshop.api.entity.OrderStatus;
import com.techshop.api.entity.Payment;
import com.techshop.api.entity.Product;
import com.techshop.api.model.CheckoutInformation;
import com.techshop.api.model.OrderProductInformation;
import com.techshop.api.service.CheckoutService;
import com.techshop.api.util.EntityResult;

@Stateless
public class CheckoutServiceImpl implements CheckoutService {

	@EJB
	private CustomerDAO customerDAO;
	@EJB
	private OrderDAO orderDAO;
	@EJB
	private OrderDetailDAO orderDetailDAO;
	@EJB
	private PaymentDAO paymentDAO;
	@EJB
	private ProductDAO productDAO;
	
	@Override
	public EntityResult save(CheckoutInformation model) {
		Customer customer = this.parseCustomerInformation(model);
		if (!customerDAO.save(customer).getSuccess()) {
			return new EntityResult(null, "fail", false);
		}
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setStatus(OrderStatus.PENDING.getValue());
		order.setOrderDate(LocalDate.now());
		Integer total = 0;
		for (OrderProductInformation opi : model.getOrderDetails()) {
			Product product = productDAO.findOne(opi.getProductId()).getData();
			total += product.getPrice() * opi.getQuantity();
		}
		
		Payment payment = new Payment();
		payment.setCreatedDate(LocalDate.now());
		payment.setStatus(OrderStatus.PENDING.getValue());
		if (model.getDeliveryType().equals("free")) {
			payment.setShipDate(LocalDate.now().plusDays(3));
		}
		else {
			payment.setShipDate(LocalDate.now().plusDays(1));
		}
		payment.setTotalPrice(total);
		order.setPayment(payment);
		
		if (!paymentDAO.save(payment).getSuccess()) {
			return new EntityResult(null, "fail", false);
		}		
		if (!orderDAO.save(order).getSuccess()) {
			return new EntityResult(null, "fail", false);
		}
		
		for (OrderProductInformation opi : model.getOrderDetails()) {
			OrderDetail od = new OrderDetail();
			Product product = productDAO.findOne(opi.getProductId()).getData();
			od.setOrderId(order);
			od.setProductId(product);
			od.setQuantity(opi.getQuantity());
			if (!orderDetailDAO.save(od).getSuccess()) {
				return new EntityResult(null, "fail", false);
			}
			total += product.getPrice() * opi.getQuantity();
		}
		
		return new EntityResult(order.getId(), "success", true);
	}
	
	private Customer parseCustomerInformation(CheckoutInformation model) {
		Customer customer = new Customer();
		customer.setAddress(model.getAddress());
		customer.setCity(model.getCity());
		customer.setCompany(model.getCompany());
		customer.setEmail(model.getEmail());
		customer.setFullname(model.getFullname());
		customer.setPhoneNumber(model.getPhoneNumber());
		return customer;
	}
	
}
