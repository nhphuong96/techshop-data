package com.techshop.api.validator.impl;

import java.lang.reflect.Field;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.techshop.api.validator.RequirementValidator;

@Stateless
public class RequirementValidatorImpl implements RequirementValidator {

	private static final String ERROR_OCCURED = "Error occured: ";
	private final static Logger logger = Logger.getLogger(RequirementValidatorImpl.class);
	
	@Override
	public boolean validateObjectPropertiesNotNull(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				Object value = f.get(object);
				if (value == null) {
					logger.error(ERROR_OCCURED + f.getName() + " is null");
					return false;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(ERROR_OCCURED + e.getMessage(), e);
				return false;
			}
		}
		return true;
	}

}
