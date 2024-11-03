/**
 * 
 */
package com.rohan.grocery_booking.common.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * @author rrohan419@gmail.com
 */
@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class CustomExceptionModel {

	private String message;

	private Integer status;

	private LocalDateTime timestamp;
}
