/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;

import com.rohan.grocery_booking.grocery.dto.MasterGroceryDto;
import com.rohan.grocery_booking.grocery.dto.UpdateMasterGroceryDto;
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
	
	/**
	 * remove grocery item from master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuids
	 * @param userUuid
	 */
	void removeMasterGroceryItemByUuid(List<String> uuids, String userUuid);
	
	/**
	 * update master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryDtos
	 * @param userUuid
	 * @return {@link List}
	 * @see MasterGroceryModel
	 */
	List<MasterGroceryModel> updateMasterGroceryProducts(List<UpdateMasterGroceryDto> masterGroceryDtos, String userUuid);
}
