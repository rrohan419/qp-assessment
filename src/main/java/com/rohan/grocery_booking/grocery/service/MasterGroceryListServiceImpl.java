/**
 * 
 */
package com.rohan.grocery_booking.grocery.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.common.util.Mapper;
import com.rohan.grocery_booking.grocery.dao.MasterGroceryListDao;
import com.rohan.grocery_booking.grocery.dto.MasterGroceryDto;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Service
@RequiredArgsConstructor
public class MasterGroceryListServiceImpl implements MasterGroceryListService {

	private final MasterGroceryListDao masterGroceryListDao;
	private final Mapper mapper;

	@Override
	public MasterGroceryModel addGroceryInMaster(MasterGroceryDto masterGroceryDto, String userUuid) {
		boolean exists = masterGroceryListDao.groceryExistsByName(masterGroceryDto.getName());

		if (exists) {
			throw new CustomException("grocery with name " + masterGroceryDto.getName() + " already exists.",
					HttpStatus.CONFLICT);
		} else {
			MasterGroceryList masterGroceryList = MasterGroceryList.builder().uuid(UUID.randomUUID().toString()).category(masterGroceryDto.getCategory())
					.name(masterGroceryDto.getName()).currency("INR").price(masterGroceryDto.getPrice())
					.quantity(masterGroceryDto.getQuantity()).quantityUnit(masterGroceryDto.getQuantityUnit()).createdBy(userUuid).build();
			
			return mapper.convert(masterGroceryListDao.saveGrocery(masterGroceryList), MasterGroceryModel.class);
		}
	}

	@Override
	public List<MasterGroceryModel> getMasterGroceryList(String userUuid) {
		return mapper.convertToList(masterGroceryListDao.fetchAllActiveList(), MasterGroceryModel.class);
	}

}
