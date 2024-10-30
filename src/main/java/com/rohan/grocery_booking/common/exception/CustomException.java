/**
 * 
 */
package com.rohan.grocery_booking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Getter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Http status
	 */
	private final HttpStatus httpStatus;

	/**
	 * Detailed error message
	 */
	private final String detail;
	
	/**
	 * Constructs new custom exception with message, error description and http
	 * status
	 * 
	 * @author rrohan419@gmail.com
	 * @param message
	 * @param detail
	 * @param httpStatus
	 */
	public CustomException(String message, String detail, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
		this.detail = detail;
	}

	/**
	 * Constructs new custom exception with message and http status
	 * 
	 * @author rrohan419@gmail.com
	 * @param message
	 * @param httpStatus
	 */
	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
		this.detail = null;
	}
}
