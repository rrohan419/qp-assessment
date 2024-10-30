/**
 * 
 */
package com.rohan.grocery_booking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.user.entity.User;

/**
 * @author rrohan419@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User findByUuid(String uuid);
}
