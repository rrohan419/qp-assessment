/**
 * 
 */
package com.rohan.grocery_booking.grocery.model;

import java.util.List;

import lombok.Data;

/**
 * @author rrohan419@gmail.com
 */
@Data
public class UserGroceryCollectionModel {

	private String uuid;
	
	private String name;
	
	private List<UserGroceryListModel> userGroceryLists;

}
