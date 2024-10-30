/**
 * 
 */
package com.rohan.grocery_booking.user.service;

import com.rohan.grocery_booking.user.dto.UserOnboardingDto;
import com.rohan.grocery_booking.user.entity.User;

/**
 * @author rrohan419@gmail.com
 */
public interface UserService {

	User onboardUser(UserOnboardingDto userOnboardingDto);
}
