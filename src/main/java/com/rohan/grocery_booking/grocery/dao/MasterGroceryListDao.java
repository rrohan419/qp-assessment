/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;

/**
 * @author rrohan419@gmail.com
 */
public interface MasterGroceryListDao {

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryList
	 * @return
	 */
	MasterGroceryList saveGrocery(MasterGroceryList masterGroceryList);
	
	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryLists
	 * @return
	 */
	List<MasterGroceryList> saveAllGrocries(List<MasterGroceryList> masterGroceryLists);
	
	/**
	 * fetch all active master list
	 *  
	 * @author rrohan419@gmail.com
	 *
	 * @return{@link List}
	 * @see MasterGroceryList
	 */
	List<MasterGroceryList> fetchAllActiveList();
	
	/**
	 * delete grocery by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuids
	 */
	void deleteGrocriesByUuids(List<String> uuids);
	
	/**
	 * check if grocery by name exists
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param name
	 * @return{@link boolean}
	 */
	boolean groceryExistsByName(String name);
	
	/**
	 * master grocery item by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuid
	 * @return {@link MasterGroceryList}
	 */
	MasterGroceryList masterGroceryByUuid(String uuid);
	
}
