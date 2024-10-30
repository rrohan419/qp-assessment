/**
 * 
 */
package com.rohan.grocery_booking.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.user.dao.UserDao;
import com.rohan.grocery_booking.user.dto.UserOnboardingDto;
import com.rohan.grocery_booking.user.entity.Role;
import com.rohan.grocery_booking.user.entity.User;
import com.rohan.grocery_booking.user.entity.UserRole;
import com.rohan.grocery_booking.user.repository.RoleRepository;
import com.rohan.grocery_booking.user.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final RoleRepository roleRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Override
	public User onboardUser(UserOnboardingDto userOnboardingDto) {
		User user = new User();
		user.setFirstName(userOnboardingDto.getFirstName());
		user.setLastName(userOnboardingDto.getLastName());
		user.setEmail(userOnboardingDto.getEmail());
		user.setPhoneNumber(userOnboardingDto.getPhoneNumber());
		user.setUuid(UUID.randomUUID().toString());
		
		user = userDao.saveUser(user);
		
		UserType userType = userOnboardingDto.isAdmin() ? UserType.ADMIN : UserType.USER;
		UserRole userRole = addRoleToUser(user, userType);
		
		user.setUserRoles(List.of(userRole));
		
		return userDao.saveUser(user);
	}
	
	public UserRole addRoleToUser(User user, UserType userType) {
Role role = roleRepository.findByUserType(userType);
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		return userRoleRepository.save(userRole);
	}

}
