/**
 * 
 */
package com.rohan.grocery_booking.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.grocery.entity.UserGroceryCollection;

/**
 * @author rrohan419@gmail.com
 */
public interface UserGroceryCollectionRepository extends JpaRepository<UserGroceryCollection, Long> {

	List<UserGroceryCollection> findByUserUuid(String userUuid);
	
	UserGroceryCollection findByUuid(String uuid);
}
