package com.app.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
	
	private HttpStatus status;
	private String message;
	
	public BlogAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
    public BlogAPIException(String message1, HttpStatus status, String message2) {
    	super(message1);
    	this.status = status;
    	this.message = message2;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
