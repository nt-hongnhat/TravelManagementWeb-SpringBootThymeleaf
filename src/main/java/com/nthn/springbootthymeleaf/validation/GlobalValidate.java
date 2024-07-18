package com.nthn.springbootthymeleaf.validation;

import org.springframework.validation.Errors;

public interface GlobalValidate {
	
	void validateNotNull(Object object, String table, String field, Errors errors
	);
}
