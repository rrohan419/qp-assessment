/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import com.rohan.grocery_booking.grocery.dto.UserGroceryDto;

/**
 * @author rrohan419@gmail.com
 */
public interface UserGroceryService {

	/**
	 * save or update user grocery
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryDto
	 * @param userUuid
	 */
	void saveOrUpdateUserGrocery(UserGroceryDto userGroceryDto, String userUuid);
}
