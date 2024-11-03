/**
 * 
 */
package com.rohan.grocery_booking.grocery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterGroceryModel {

	private String uuid;
	
	private String name;
	
	private Double price;
	
	private String currency;
	
	private Double quantity;
	
	private String quantityUnit;
	
	private String category;
	
	
}
