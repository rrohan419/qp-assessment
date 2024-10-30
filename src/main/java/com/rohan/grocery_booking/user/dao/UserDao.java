/**
 * 
 */
package com.rohan.grocery_booking.user.dao;

import com.rohan.grocery_booking.user.entity.User;

/**
 * @author rrohan419@gmail.com
 */
public interface UserDao {

	User saveUser(User user);
	
	User userByEmail(String email);
	
	User userByUuid(String uuid);
}
