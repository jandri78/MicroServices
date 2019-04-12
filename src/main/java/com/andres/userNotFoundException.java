package com.andres;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class userNotFoundException extends Exception {

	
	public userNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

}
