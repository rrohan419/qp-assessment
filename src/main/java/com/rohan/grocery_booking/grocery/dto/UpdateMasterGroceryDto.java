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
public class UpdateMasterGroceryDto {

	@NotBlank
	private String masterGroceryUuid;
	
	@NotNull
	private MasterGroceryDto masterGrocery;
}
