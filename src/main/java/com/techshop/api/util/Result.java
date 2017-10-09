package com.techshop.api.util;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "data" })
public class Result<T> {
	
	
	private T data;
	
	private String message;
	private Boolean success;
	
	public Result() {}
	
	public Result(T data) {
		this.data = data;
		this.message = "success";
		this.success = true;
	}
	
	public Result(String statusMessage, Boolean isSuccess) {
		this.data = null;
		this.message = statusMessage;
		this.success = isSuccess;
	}
	
	public Result(T data, String status, Boolean isSuccess) {
		this.data = data;
		this.message = status;
		this.success = isSuccess;
	}
}
