/**
 * 
 */
package com.rohan.grocery_booking.grocery.dto;

import java.util.List;

import lombok.Getter;

/**
 * @author rrohan419@gmail.com
 */
@Getter
public class UserGroceryDto {

	private String name;

	private String userGroceryCollectionUuid;
	
	private List<UserGroceryListDto> userGroceryList;
}
