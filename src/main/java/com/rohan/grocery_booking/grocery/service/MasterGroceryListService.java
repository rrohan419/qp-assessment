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

	MasterGroceryModel addGroceryInMaster(MasterGroceryDto masterGroceryDto, String userUuid);
	
	List<MasterGroceryModel> getMasterGroceryList(String userUuid);
}
