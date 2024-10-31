/**
 * 
 */
package com.rohan.grocery_booking.user.service;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.user.dto.UserOnboardingDto;
import com.rohan.grocery_booking.user.entity.User;
import com.rohan.grocery_booking.user.entity.UserRole;

/**
 * @author rrohan419@gmail.com
 */
public interface UserService {

	/**
	 * 
	 * @author rrohan@gmail.com
	 *
	 * @param userOnboardingDto
	 * @return
	 */
	User onboardUser(UserOnboardingDto userOnboardingDto);
	
	/**
	 * 
	 * @author rrohan@gmail.com
	 *
	 * @param user
	 * @param userType
	 * @return {@link UserRole}
	 */
	UserRole addRoleToUser(User user, UserType userType);
	
	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @param userType
	 * @return
	 */
	void matchUserRole(String userUuid, UserType userType);
}
