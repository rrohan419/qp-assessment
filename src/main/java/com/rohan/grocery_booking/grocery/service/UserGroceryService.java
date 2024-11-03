/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;

import com.rohan.grocery_booking.grocery.dto.UserGroceryDto;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;
import com.rohan.grocery_booking.grocery.model.UserGroceryCollectionModel;

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
	UserGroceryCollectionModel createOrUpdateUserGrocery(UserGroceryDto userGroceryDto, String userUuid);
	
	/**
	 * get list of user grocery collection by user uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@klink List}
	 * @see UserGroceryCollectionModel
	 */
	List<UserGroceryCollectionModel> userGroceryCollectionsByUserUuid(String userUuid);


	/**
	 * get all active groceries
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @return {@klink List}
	 * @see MasterGroceryModel
	 */
	List<MasterGroceryModel> getAllGroceries();
}
