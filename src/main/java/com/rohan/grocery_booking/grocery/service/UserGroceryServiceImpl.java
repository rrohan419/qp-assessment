/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.grocery.dao.MasterGroceryListDao;
import com.rohan.grocery_booking.grocery.dao.UserGroceryCollectionDao;
import com.rohan.grocery_booking.grocery.dao.UserGroceryListDao;
import com.rohan.grocery_booking.grocery.dto.UserGroceryDto;
import com.rohan.grocery_booking.grocery.dto.UserGroceryListDto;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;
import com.rohan.grocery_booking.grocery.entity.UserGroceryCollection;
import com.rohan.grocery_booking.grocery.entity.UserGroceryList;
import com.rohan.grocery_booking.user.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Service
@RequiredArgsConstructor
public class UserGroceryServiceImpl implements UserGroceryService {

	private final UserGroceryListDao userGroceryListDao;
	private final UserGroceryCollectionDao userGroceryCollectionDao;
	private final MasterGroceryListDao masterGroceryListDao;
	private final UserService userService;

	/**
	 * save or update user grocery
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryDto
	 * @param userUuid
	 */
	@Override
	public void saveOrUpdateUserGrocery(UserGroceryDto userGroceryDto, String userUuid) {
		userService.matchUserRole(userUuid, UserType.USER);

		if (userGroceryDto.getUserGroceryCollectionUuid() != null
				&& !userGroceryDto.getUserGroceryCollectionUuid().isBlank()) {

			UserGroceryCollection groceryCollection = userGroceryCollectionDao
					.userGroceryCollectionByUuid(userGroceryDto.getUserGroceryCollectionUuid());

			if (groceryCollection != null) {

				Map<String, UserGroceryList> userGroceryListMap = groceryCollection.getUserGroceryLists().stream()
						.collect(Collectors.toMap(UserGroceryList::getUuid, userGroceryList -> userGroceryList));

				List<UserGroceryList> userGroceryLists = userGroceryDto.getUserGroceryList().stream()
						.map(userGroceryListDto -> {

							String groceryListUuid = userGroceryListDto.getUserGroceryListUuid();

							if (groceryListUuid != null && !groceryListUuid.isBlank()) {
								UserGroceryList savedUserGroceryList = userGroceryListMap
										.get(userGroceryListDto.getUserGroceryListUuid());

								if (savedUserGroceryList != null) {
									updateGroceryList(savedUserGroceryList, userGroceryListDto);
									return savedUserGroceryList;
								} else {
									return createUserGroceryList(userGroceryListDto);
								}
							}
							return createUserGroceryList(userGroceryListDto);
						}).toList();

				groceryCollection.setUserGroceryLists(userGroceryLists);
				userGroceryCollectionDao.saveUserGroceryCollection(groceryCollection);

			} else {
				UserGroceryCollection userGroceryCollection = createNewUserGroceryCollection(userGroceryDto, userUuid);
				
				userGroceryCollectionDao.saveUserGroceryCollection(userGroceryCollection);
			}
		} else {

			UserGroceryCollection userGroceryCollection = createNewUserGroceryCollection(userGroceryDto, userUuid);
			
			userGroceryCollectionDao.saveUserGroceryCollection(userGroceryCollection);
		}
	}
	
	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryDto
	 * @param userUuid
	 * @return {@link UserGroceryCollection}
	 */
	private UserGroceryCollection createNewUserGroceryCollection(UserGroceryDto userGroceryDto, String userUuid) {
		UserGroceryCollection userGroceryCollection = new UserGroceryCollection();
		userGroceryCollection.setName(userGroceryDto.getName());
		userGroceryCollection.setUuid(UUID.randomUUID().toString());
		userGroceryCollection.setUserUuid(userUuid);

		List<UserGroceryList> userGroceryLists = userGroceryDto.getUserGroceryList().stream()
				.map(this::createUserGroceryList).toList();

		userGroceryLists = userGroceryListDao.saveAllGroceryLists(userGroceryLists);

		userGroceryCollection.setUserGroceryLists(userGroceryLists);
		
		return userGroceryCollection;
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryListDto
	 * @return {@link UserGroceryList}
	 */
	private UserGroceryList createUserGroceryList(UserGroceryListDto userGroceryListDto) {

		MasterGroceryList masterGroceryList = masterGroceryListDao
				.masterGroceryByUuid(userGroceryListDto.getMasterGroceryListUuid());

		UserGroceryList userGroceryList = new UserGroceryList();
		userGroceryList.setUuid(UUID.randomUUID().toString());
		userGroceryList.setMasterGroceryList(masterGroceryList);

		updateGroceryList(userGroceryList, userGroceryListDto);

		return userGroceryList;
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryList
	 * @param userGroceryListDto
	 */
	private void updateGroceryList(UserGroceryList userGroceryList, UserGroceryListDto userGroceryListDto) {
		MasterGroceryList masterGroceryList = userGroceryList.getMasterGroceryList();

		userGroceryList.setQuantity(userGroceryListDto.getQuantity());
		userGroceryList.setName(masterGroceryList.getName());
		userGroceryList.setPrice(masterGroceryList.getPrice());
	}

}
