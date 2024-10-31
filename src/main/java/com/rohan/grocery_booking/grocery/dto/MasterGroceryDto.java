/**
 * 
 */
package com.rohan.grocery_booking.grocery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author rrohan419@gmail.com
 */
@Getter
public class MasterGroceryDto {

	@NotBlank
	private String name;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	@NotBlank
	private String quantityUnit;
	
	@NotBlank
	private String category;
	
	
}
