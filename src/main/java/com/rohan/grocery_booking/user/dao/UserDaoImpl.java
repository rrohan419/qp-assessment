/**
 * 
 */
package com.rohan.grocery_booking.user.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.user.entity.User;
import com.rohan.grocery_booking.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public User userByEmail(String email) {
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public User userByUuid(String uuid) {
		try {
			return userRepository.findByUuid(uuid);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
