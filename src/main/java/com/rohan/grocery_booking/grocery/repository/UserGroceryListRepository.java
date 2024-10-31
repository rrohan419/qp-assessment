/**
 * 
 */
package com.rohan.grocery_booking.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.grocery.entity.UserGroceryList;

/**
 * @author rrohan419@gmail.com
 */
public interface UserGroceryListRepository extends JpaRepository<UserGroceryList, Long> {

}
