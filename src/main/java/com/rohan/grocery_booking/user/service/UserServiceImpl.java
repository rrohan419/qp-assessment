/**
 * 
 */
package com.rohan.grocery_booking.user.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.user.dao.UserDao;
import com.rohan.grocery_booking.user.dto.UserOnboardingDto;
import com.rohan.grocery_booking.user.entity.Role;
import com.rohan.grocery_booking.user.entity.User;
import com.rohan.grocery_booking.user.entity.UserRole;
import com.rohan.grocery_booking.user.repository.RoleRepository;
import com.rohan.grocery_booking.user.repository.UserRoleRepository;

import jakarta.transaction.Transactional;
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

	/**
	 * 
	 * @author rrohan@gmail.com
	 *
	 * @param userOnboardingDto
	 * @return
	 */
	@Transactional
	@Override
	public User onboardUser(UserOnboardingDto userOnboardingDto) {
		if (userDao.userByEmail(userOnboardingDto.getEmail()) != null) {
			throw new CustomException(
					"user with email : " + userOnboardingDto.getEmail() + " already present. Please Login!",
					HttpStatus.CONFLICT);
		}

		User user = new User();
		user.setFirstName(userOnboardingDto.getFirstName());
		user.setLastName(userOnboardingDto.getLastName());
		user.setEmail(userOnboardingDto.getEmail());
		user.setPhoneNumber(userOnboardingDto.getPhoneNumber());
		user.setUuid(UUID.randomUUID().toString());

		user = userDao.saveUser(user);

		UserType userType = userOnboardingDto.isAdmin() ? UserType.ADMIN : UserType.USER;
		addRoleToUser(user, userType);

		return user;
	}

	/**
	 * 
	 * @author rrohan@gmail.com
	 *
	 * @param user
	 * @param userType
	 * @return {@link UserRole}
	 */
	@Override
	public UserRole addRoleToUser(User user, UserType userType) {
		Role role = roleRepository.findByUserType(userType);

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		return userRoleRepository.save(userRole);
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @param userType
	 * @return
	 */
	@Override
	public void matchUserRole(String userUuid, UserType userType) {
		User user = userDao.userByUuid(userUuid);
		
		if(user != null && user.getUserTypeSet() != null && !user.getUserTypeSet().isEmpty() && user.getUserTypeSet().contains(userType)) {
//			return user.getUserTypeSet().contains(userType);
		} else {
			throw new CustomException("You don't have the required permission or you are not registered.", HttpStatus.UNAUTHORIZED);
		}
	}

}
