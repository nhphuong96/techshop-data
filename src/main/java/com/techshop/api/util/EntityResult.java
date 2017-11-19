package com.techshop.api.util;

public class EntityResult {
	private Long id;
	private String message;
	private Boolean success;
	
	public EntityResult() {}
	
	public EntityResult(Long id, String message, Boolean success) {
		super();
		this.id = id;
		this.message = message;
		this.success = success;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
