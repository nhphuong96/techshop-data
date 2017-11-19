package com.techshop.api.model;

import java.util.List;

public class CheckoutInformation {
	
	private String fullname;
	private String email;
	private String address;
	private String city;
	private String phoneNumber;
	private String company;
	private String deliveryType;
	private List<OrderProductInformation> orderDetails;
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public List<OrderProductInformation> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderProductInformation> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}

