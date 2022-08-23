package com.java.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateModel {
	private static Map<String, String> ERROR_MAP = new HashMap<String, String>();

	public static Map<String, String> Validate(Object object) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				ERROR_MAP.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
			}
		}
		return ERROR_MAP;

	}
}
