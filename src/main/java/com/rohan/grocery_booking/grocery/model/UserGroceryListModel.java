/**
 * 
 */
package com.rohan.grocery_booking.grocery.model;

import lombok.Data;

/**
 * @author rrohan419@gmail.com
 */
@Data
public class UserGroceryListModel {

	private String uuid;
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	
	private String masterGroceryListUuid;
	
}
