/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;

import com.rohan.grocery_booking.grocery.dto.MasterGroceryDto;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;

/**
 * @author rrohan419@gmail.com
 */
public interface MasterGroceryListService {

	/**
	 * add grocery by admin user
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryDto
	 * @param userUuid
	 * @return {@link MasterGroceryModel}
	 */
	MasterGroceryModel addGroceryInMaster(MasterGroceryDto masterGroceryDto, String userUuid);
	
	/**
	 * get all master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@link List}
	 * @see MasterGroceryModel
	 */
	List<MasterGroceryModel> getMasterGroceryList(String userUuid);
}
