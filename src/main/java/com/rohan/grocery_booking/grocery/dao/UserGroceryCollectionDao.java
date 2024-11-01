/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import com.rohan.grocery_booking.grocery.entity.UserGroceryCollection;

/**
 * @author rrohan419@gmail.com
 */
public interface UserGroceryCollectionDao {

	/**
	 * save user grocery collection
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryCollection
	 * @return {@link UserGroceryCollection}
	 */
	UserGroceryCollection saveUserGroceryCollection(UserGroceryCollection userGroceryCollection);
	
	/**
	 * get all user grocery collection
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@link List}
	 * @see UserGroceryCollection
	 */
	List<UserGroceryCollection> getAllGroceryCollectionByUserUuid(String userUuid);
	
	/**
	 * get user grocery collection by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuid
	 * @return {@link UserGroceryCollection}
	 */
	UserGroceryCollection userGroceryCollectionByUuid(String uuid);
}
