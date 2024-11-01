/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import com.rohan.grocery_booking.grocery.entity.UserGroceryList;

/**
 * @author rrohan419@gmail.com
 */
public interface UserGroceryListDao {

	/**
	 * save user grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryList
	 * @return {@link UserGroceryList}
	 */
	UserGroceryList saveUserGroceryList(UserGroceryList userGroceryList);
	
	/**
	 * save all user grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryLists
	 * @return {@link List}
	 * @see UserGroceryList
	 */
	List<UserGroceryList> saveAllGroceryLists(List<UserGroceryList> userGroceryLists);
	
}
