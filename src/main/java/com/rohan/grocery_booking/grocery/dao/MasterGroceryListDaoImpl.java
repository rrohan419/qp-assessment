/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rohan.grocery_booking.common.exception.CustomException;
import com.rohan.grocery_booking.grocery.entity.MasterGroceryList;
import com.rohan.grocery_booking.grocery.repository.MasterGroceryListRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Repository
@RequiredArgsConstructor
public class MasterGroceryListDaoImpl implements MasterGroceryListDao {

	private final MasterGroceryListRepository masterGroceryListRepository;

	@Override
	public MasterGroceryList saveGrocery(MasterGroceryList masterGroceryList) {
		try {
			return masterGroceryListRepository.save(masterGroceryList);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MasterGroceryList> fetchAllActiveList() {
		try {
			return masterGroceryListRepository.findAll();
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteGrocery(String uuid) {
		try {
			masterGroceryListRepository.deleteByUuid(uuid);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public boolean groceryExistsByName(String name) {
		try {
			return masterGroceryListRepository.existsByName(name);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * master grocery item by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuid
	 * @return {@link MasterGroceryList}
	 */
	@Override
	public MasterGroceryList masterGroceryByUuid(String uuid) {
		try {
			return masterGroceryListRepository.findByUuid(uuid);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
