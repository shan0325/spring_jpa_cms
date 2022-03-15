package com.spring.cms.exception;

import lombok.Getter;

@Getter
public class AuthorityNotExistException extends RuntimeException {
	
	private Long authorityId;

	private String authority;

	public AuthorityNotExistException(Long authorityId) {
		this.authorityId = authorityId;
	}

	public AuthorityNotExistException(String authority) {
		this.authority = authority;
	}

	public AuthorityNotExistException(Long authorityId, String authority) {
		this.authorityId = authorityId;
		this.authority = authority;
	}

}
