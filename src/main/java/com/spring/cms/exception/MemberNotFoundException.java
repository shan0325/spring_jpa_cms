package com.spring.cms.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

	private String email;

	public MemberNotFoundException() {}

	public MemberNotFoundException(String email) {
		this.email = email;
	}


}
