package com.techshop.api.model;

import java.time.LocalDate;
import java.util.List;

import com.techshop.api.entity.OrderStatus;

public class CheckoutInformation {
	
	private LocalDate orderDate;
	private OrderStatus orderStatus;
	private LocalDate shipDate;
	private List<OrderProductInformation> orderDetails;
	
	public LocalDate getShipDate() {
		return shipDate;
	}
	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OrderProductInformation> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderProductInformation> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}

