/**
 * 
 */
package com.rohan.grocery_booking.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.common.enums.EntityStatus;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;

/**
 * @author rrohan419@gmail.com
 */
public interface MasterGroceryListRepository extends JpaRepository<MasterGroceryList, Long> {

	void deleteAllByUuidIn(List<String> uuid);
	
	boolean existsByName(String name);
	
	MasterGroceryList findByUuid(String uuid);
	
	List<MasterGroceryList> findByEntityStatus(EntityStatus entityStatus);
}
