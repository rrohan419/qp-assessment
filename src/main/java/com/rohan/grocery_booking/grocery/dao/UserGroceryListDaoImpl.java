/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.grocery.entity.UserGroceryList;
import com.rohan.grocery_booking.grocery.repository.UserGroceryListRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Repository
@RequiredArgsConstructor
public class UserGroceryListDaoImpl implements UserGroceryListDao {

	private final UserGroceryListRepository userGroceryListRepository;

	/**
	 * save user grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryList
	 * @return {@link UserGroceryList}
	 */
	@Override
	public UserGroceryList saveUserGroceryList(UserGroceryList userGroceryList) {
		try {
			return userGroceryListRepository.save(userGroceryList);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * save all user grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryLists
	 * @return {@link List}
	 * @see UserGroceryList
	 */
	@Override
	public List<UserGroceryList> saveAllGroceryLists(List<UserGroceryList> userGroceryLists) {
		try {
			return userGroceryListRepository.saveAll(userGroceryLists);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
