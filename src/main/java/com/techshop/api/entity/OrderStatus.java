package com.techshop.api.entity;

public enum OrderStatus {

	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	DELIVERED("Delivered");
	
	private String value;
	
	OrderStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
