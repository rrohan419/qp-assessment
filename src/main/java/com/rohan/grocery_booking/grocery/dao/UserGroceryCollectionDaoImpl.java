/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.grocery.entity.UserGroceryCollection;
import com.rohan.grocery_booking.grocery.repository.UserGroceryCollectionRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Repository
@RequiredArgsConstructor
public class UserGroceryCollectionDaoImpl implements UserGroceryCollectionDao {

	private final UserGroceryCollectionRepository userGroceryCollectionRepository;
	
	/**
	 * save user grocery collection
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryCollection
	 * @return {@link UserGroceryCollection}
	 */
	@Override
	public UserGroceryCollection saveUserGroceryCollection(UserGroceryCollection userGroceryCollection) {
		try {
			return userGroceryCollectionRepository.save(userGroceryCollection);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * get all user grocery collection
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@link List}
	 * @see UserGroceryCollection
	 */
	@Override
	public List<UserGroceryCollection> getAllGroceryCollectionByUserUuid(String userUuid) {
		try {
			return userGroceryCollectionRepository.findByUserUuid(userUuid);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * get user grocery collection by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuid
	 * @return {@link UserGroceryCollection}
	 */
	@Override
	public UserGroceryCollection userGroceryCollectionByUuid(String uuid) {
		try {
			return userGroceryCollectionRepository.findByUuid(uuid);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
