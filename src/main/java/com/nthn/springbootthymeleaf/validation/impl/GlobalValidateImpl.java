package com.nthn.springbootthymeleaf.validation.impl;

import com.nthn.springbootthymeleaf.constants.ValidatorConstants.DefaultMessage;
import com.nthn.springbootthymeleaf.constants.ValidatorConstants.ErrorCode;
import com.nthn.springbootthymeleaf.validation.GlobalValidate;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class GlobalValidateImpl implements GlobalValidate {
	
	@Override
	public void validateNotNull(Object object, String table, String field, Errors errors
	) {
		if (Objects.isNull(object)) {
			errors.rejectValue(field,
					String.format("%s.%s", table, StringUtils.upperCase(ErrorCode.NOT_NULL)),
					String.format("%s %s", field, DefaultMessage.NULL));
		}
	}
}
