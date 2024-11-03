/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.common.util.Mapper;
import com.rohan.grocery_booking.grocery.dao.MasterGroceryListDao;
import com.rohan.grocery_booking.grocery.dao.UserGroceryCollectionDao;
import com.rohan.grocery_booking.grocery.dao.UserGroceryListDao;
import com.rohan.grocery_booking.grocery.dto.UserGroceryDto;
import com.rohan.grocery_booking.grocery.dto.UserGroceryListDto;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;
import com.rohan.grocery_booking.grocery.entity.UserGroceryCollection;
import com.rohan.grocery_booking.grocery.entity.UserGroceryList;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;
import com.rohan.grocery_booking.grocery.model.UserGroceryCollectionModel;
import com.rohan.grocery_booking.user.service.UserService;

import jakarta.transaction.Transactional;
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
	private final Mapper mapper;

	/**
	 * save or update user grocery
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userGroceryDto
	 * @param userUuid
	 */
	@Transactional
	@Override
	public UserGroceryCollectionModel createOrUpdateUserGrocery(UserGroceryDto userGroceryDto, String userUuid) {
		userService.matchUserRole(userUuid, UserType.USER);

		if (userGroceryDto.getUserGroceryCollectionUuid() != null
				&& !userGroceryDto.getUserGroceryCollectionUuid().isBlank()) {

			UserGroceryCollection groceryCollection = userGroceryCollectionDao
					.userGroceryCollectionByUuid(userGroceryDto.getUserGroceryCollectionUuid());

			if (groceryCollection != null) {

				Map<String, UserGroceryList> userGroceryListMap = collectUserGroceryList(groceryCollection);

				List<UserGroceryList> userGroceryLists = userGroceryDto.getUserGroceryList().stream()
						.map(userGroceryListDto -> {

							String groceryListUuid = userGroceryListDto.getUserGroceryListUuid();

							UserGroceryList savedUserGroceryList = (groceryListUuid != null
									&& !groceryListUuid.isBlank()) ? userGroceryListMap.get(groceryListUuid) : null;

							if (savedUserGroceryList != null) {
								updateGroceryList(savedUserGroceryList, userGroceryListDto);
								return savedUserGroceryList;
							}
							return createUserGroceryList(userGroceryListDto);
						}).toList();

				userGroceryLists = userGroceryListDao.saveAllGroceryLists(userGroceryLists);
				groceryCollection.setUserGroceryLists(userGroceryLists);
				return mapper.convert(userGroceryCollectionDao.saveUserGroceryCollection(groceryCollection),
						UserGroceryCollectionModel.class);
			}
		}
		return mapper.convert(userGroceryCollectionDao.saveUserGroceryCollection(
				createNewUserGroceryCollection(userGroceryDto, userUuid)), UserGroceryCollectionModel.class);
	}

	/**
	 * get list of user grocery collection by user uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@klink List}
	 * @see UserGroceryCollectionModel
	 */
	@Override
	public List<UserGroceryCollectionModel> userGroceryCollectionsByUserUuid(String userUuid) {
		userService.matchUserRole(userUuid, UserType.USER);
		List<UserGroceryCollection> userGroceryCollections = userGroceryCollectionDao
				.getAllGroceryCollectionByUserUuid(userUuid);

		return mapper.convertToList(userGroceryCollections, UserGroceryCollectionModel.class);
	}

	/**
	 * get all active groceries
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @return {@klink List}
	 * @see MasterGroceryModel
	 */
	@Override
	public List<MasterGroceryModel> getAllGroceries() {
		List<MasterGroceryList> masterGroceryLists = masterGroceryListDao.fetchAllActiveList();
		
		return mapper.convertToList(masterGroceryLists, MasterGroceryModel.class);
	}
	
	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param groceryCollection
	 * @return {@link Map}
	 */
	private Map<String, UserGroceryList> collectUserGroceryList(UserGroceryCollection groceryCollection) {

		Map<String, UserGroceryList> userGroceryListMap = new HashMap<>();

		if (groceryCollection.getUserGroceryLists() != null && !groceryCollection.getUserGroceryLists().isEmpty()) {
			userGroceryListMap = groceryCollection.getUserGroceryLists().stream()
					.collect(Collectors.toMap(UserGroceryList::getUuid, userGroceryList -> userGroceryList));
		}

		return userGroceryListMap;
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
		
		Double leftQuantity = masterGroceryList.getLeftQuantity() +  userGroceryList.getQuantity();
		
		Double isPurachasePossible = leftQuantity - userGroceryListDto.getQuantity();
		
		if(isPurachasePossible >= 0) {
			userGroceryList.setQuantity(userGroceryListDto.getQuantity());
			userGroceryList.setName(masterGroceryList.getName());
			userGroceryList.setPrice(masterGroceryList.getPrice());
			
			masterGroceryList.setLeftQuantity(isPurachasePossible);
			masterGroceryList = masterGroceryListDao.saveGrocery(masterGroceryList);
			
			userGroceryList.setMasterGroceryList(masterGroceryList);
		} else {
			throw new CustomException("We have only "+leftQuantity +" "+masterGroceryList.getQuantityUnit()+" left in our inventory.", HttpStatus.FORBIDDEN);
		}
		
	}

	

}
