package com.nthn.springbootthymeleaf.constants;

import lombok.Getter;

@Getter
public enum Role {
	ADMIN("Administrator"), USER("User"), CUSTOMER("Customer");
	
	private final String name;
	
	Role(String name) {
		this.name = name;
	}
}
