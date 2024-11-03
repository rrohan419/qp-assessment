/**
 * 
 */
package com.rohan.grocery_booking.grocery.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rohan.grocery_booking.common.enums.EntityStatus;
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

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryList
	 * @return
	 */
	@Override
	public MasterGroceryList saveGrocery(MasterGroceryList masterGroceryList) {
		try {
			return masterGroceryListRepository.save(masterGroceryList);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * fetch all active master list
	 *  
	 * @author rrohan419@gmail.com
	 *
	 * @return{@link List}
	 * @see MasterGroceryList
	 */
	@Override
	public List<MasterGroceryList> fetchAllActiveList() {
		try {
			return masterGroceryListRepository.findByEntityStatus(EntityStatus.ACTIVE);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * delete grocery by uuid
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuids
	 */
	@Override
	public void deleteGrocriesByUuids(List<String> uuids) {
		try {
			masterGroceryListRepository.deleteAllByUuidIn(uuids);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * check if grocery by name exists
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param name
	 * @return{@link boolean}
	 */
	@Override
	public boolean groceryExistsByName(String name) {
		try {
			return masterGroceryListRepository.existsByNameAndEntityStatus(name, EntityStatus.ACTIVE);
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

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryLists
	 * @return
	 */
	@Override
	public List<MasterGroceryList> saveAllGrocries(List<MasterGroceryList> masterGroceryLists) {
		try {
			return masterGroceryListRepository.saveAll(masterGroceryLists);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
