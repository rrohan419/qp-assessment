/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.enums.EntityStatus;
import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.common.util.Mapper;
import com.rohan.grocery_booking.grocery.dao.MasterGroceryListDao;
import com.rohan.grocery_booking.grocery.dto.MasterGroceryDto;
import com.rohan.grocery_booking.grocery.dto.UpdateMasterGroceryDto;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;
import com.rohan.grocery_booking.user.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Service
@RequiredArgsConstructor
public class MasterGroceryListServiceImpl implements MasterGroceryListService {

	private final MasterGroceryListDao masterGroceryListDao;
	private final UserService userService;
	private final Mapper mapper;

	/**
	 * add grocery by admin user
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryDto
	 * @param userUuid
	 * @return {@link MasterGroceryModel}
	 */
	@Override
	public MasterGroceryModel addGroceryInMaster(MasterGroceryDto masterGroceryDto, String userUuid) {
		userService.matchUserRole(userUuid, UserType.ADMIN);
		boolean exists = masterGroceryListDao.groceryExistsByName(masterGroceryDto.getName());

		if (exists) {
			throw new CustomException("grocery with name " + masterGroceryDto.getName() + " already exists.",
					HttpStatus.CONFLICT);
		} else {
			MasterGroceryList masterGroceryList = MasterGroceryList.builder().uuid(UUID.randomUUID().toString())
					.category(masterGroceryDto.getCategory()).name(masterGroceryDto.getName()).currency("INR")
					.price(masterGroceryDto.getPrice()).leftQuantity(masterGroceryDto.getQuantity())
					.quantity(masterGroceryDto.getQuantity()).quantityUnit(masterGroceryDto.getQuantityUnit())
					.createdBy(userUuid).build();

			return mapper.convert(masterGroceryListDao.saveGrocery(masterGroceryList), MasterGroceryModel.class);
		}
	}

	/**
	 * get all master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@link List}
	 * @see MasterGroceryModel
	 */
	@Override
	public List<MasterGroceryModel> getMasterGroceryList(String userUuid) {
		userService.matchUserRole(userUuid, UserType.ADMIN);
		return mapper.convertToList(masterGroceryListDao.fetchAllActiveList(), MasterGroceryModel.class);
	}

	/**
	 * remove grocery item from master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuids
	 * @param userUuid
	 */
	@Override
	public void removeMasterGroceryItemByUuid(List<String> uuids, String userUuid) {
		userService.matchUserRole(userUuid, UserType.ADMIN);

		List<MasterGroceryList> masterGroceryLists = uuids.stream().map(uuid -> {
			MasterGroceryList masterGroceryItem = masterGroceryListDao.masterGroceryByUuid(uuid);
			if (masterGroceryItem == null) {
				throw new CustomException("grocery list not found with uuid : " + uuid, HttpStatus.NOT_FOUND);
			}
			masterGroceryItem.setEntityStatus(EntityStatus.DELETED);
			return masterGroceryItem;
		}).toList();
		masterGroceryListDao.saveAllGrocries(masterGroceryLists);

	}

	/**
	 * update master grocery list
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryDtos
	 * @param userUuid
	 * @return {@link List}
	 * @see MasterGroceryModel
	 */
	@Override
	public List<MasterGroceryModel> updateMasterGroceryProducts(List<UpdateMasterGroceryDto> masterGroceryDtos,
			String userUuid) {

		userService.matchUserRole(userUuid, UserType.ADMIN);

		List<MasterGroceryList> updatedMasterGroceryLists = masterGroceryDtos.stream().map(masterGroceryDto -> {
			MasterGroceryList masterGroceryList = masterGroceryListDao
					.masterGroceryByUuid(masterGroceryDto.getMasterGroceryUuid());
			MasterGroceryDto groceryDetailDto = masterGroceryDto.getMasterGrocery();
			masterGroceryList.setName(groceryDetailDto.getName());
			masterGroceryList.setPrice(groceryDetailDto.getPrice());
			masterGroceryList.setQuantity(groceryDetailDto.getQuantity());
			masterGroceryList.setQuantityUnit(groceryDetailDto.getQuantityUnit());
			masterGroceryList.setCategory(groceryDetailDto.getCategory());

			return masterGroceryList;
		}).toList();

		return mapper.convertToList(masterGroceryListDao.saveAllGrocries(updatedMasterGroceryLists),
				MasterGroceryModel.class);
	}

}
