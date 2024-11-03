/**
 * 
 */
package com.rohan.grocery_booking.grocery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.grocery_booking.grocery.dto.MasterGroceryDto;
import com.rohan.grocery_booking.grocery.dto.UpdateMasterGroceryDto;
import com.rohan.grocery_booking.grocery.model.MasterGroceryModel;
import com.rohan.grocery_booking.grocery.service.MasterGroceryListService;
import com.rohan.grocery_booking.user.constant.ApiUrl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@RestController
@RequestMapping(ApiUrl.V1_BASE_URL)
@RequiredArgsConstructor
public class MasterGroceryListController {

	private final MasterGroceryListService masterGroceryListService;

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param masterGroceryDto
	 * @param userUuid
	 * @return {@link ResponseEntity}
	 * @see MasterGroceryModel
	 */
	@PostMapping(ApiUrl.ADMIN_ADD_GROCERY)
	public ResponseEntity<MasterGroceryModel> saveGroceryList(@RequestBody @Valid MasterGroceryDto masterGroceryDto,
			@RequestParam(required = true) String userUuid) {
		MasterGroceryModel respose = masterGroceryListService.addGroceryInMaster(masterGroceryDto, userUuid);
		return new ResponseEntity<>(respose, HttpStatus.OK);
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param userUuid
	 * @return {@link ResponseEntity}
	 * @see MasterGroceryModel
	 */
	@GetMapping(ApiUrl.ADMIN_FETCH_ALL_GROCERY)
	public ResponseEntity<List<MasterGroceryModel>> fetchAllGroceryList(
			@RequestParam(required = true) String userUuid) {
		List<MasterGroceryModel> respose = masterGroceryListService.getMasterGroceryList(userUuid);
		return new ResponseEntity<>(respose, HttpStatus.OK);
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param uuids
	 * @param userUuid
	 * @return {@link ResponseEntity}
	 */
	@PostMapping(ApiUrl.ADMIN_REMOVE_GROCERY)
	public ResponseEntity<String> removeGroceryList(@RequestBody(required = true) List<String> uuids,
			@RequestParam(required = true) String userUuid) {
		masterGroceryListService.removeMasterGroceryItemByUuid(uuids, userUuid);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @param updateMasterGroceryDtos
	 * @param userUuid
	 * @return {@link ResponseEntity}
	 * @see MasterGroceryModel
	 */
	@PostMapping(ApiUrl.ADMIN_UPDATE_GROCERY)
	public ResponseEntity<List<MasterGroceryModel>> updateGroceryList(
			@RequestBody @Valid List<UpdateMasterGroceryDto> updateMasterGroceryDtos,
			@RequestParam(required = true) String userUuid) {
		List<MasterGroceryModel> response = masterGroceryListService
				.updateMasterGroceryProducts(updateMasterGroceryDtos, userUuid);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
