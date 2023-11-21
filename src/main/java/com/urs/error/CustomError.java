package com.urs.error;

import com.urs.dto.UserDTO;

public class CustomError extends UserDTO {
	private String message;

	public CustomError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
