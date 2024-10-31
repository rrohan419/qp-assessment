/**
 * 
 */
package com.rohan.grocery_booking.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;

/**
 * @author rrohan419@gmail.com
 */
public interface MasterGroceryListRepository extends JpaRepository<MasterGroceryList, Long> {

	void deleteByUuid(String uuid);
	
	boolean existsByName(String name);
}
