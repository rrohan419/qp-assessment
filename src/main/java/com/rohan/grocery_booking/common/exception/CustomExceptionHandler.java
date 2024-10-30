package com.rohan.grocery_booking.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rohan.grocery_booking.common.model.CustomExceptionModel;

/**
 * @author rrohan419@gmail.com
 *
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Custom exception handler
	 * 
	 * @author rrohan419@gmail.com
	 * @param <T>
	 * @param customException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(CustomException.class)
	public <T> ResponseEntity<CustomExceptionModel> customExceptionHandler(CustomException customException) {
		CustomExceptionModel customExceptionModel = CustomExceptionModel.builder()
				.status(customException.getHttpStatus().value()).message(customException.getMessage())
				.timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(customExceptionModel, customException.getHttpStatus());
	}

}
