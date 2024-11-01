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

import com.rohan.grocery_booking.grocery.dto.UserGroceryDto;
import com.rohan.grocery_booking.grocery.model.UserGroceryCollectionModel;
import com.rohan.grocery_booking.grocery.service.UserGroceryService;
import com.rohan.grocery_booking.user.constant.ApiUrl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@RestController
@RequestMapping(ApiUrl.V1_BASE_URL)
@RequiredArgsConstructor
public class UserGroceryListController {

	private final UserGroceryService userGroceryService;

	@PostMapping(ApiUrl.USER_ADD_GROCERY)
	public ResponseEntity<UserGroceryCollectionModel> saveGroceryList(@RequestBody @Valid UserGroceryDto userGroceryDto,
			@RequestParam(required = true) String userUuid) {
		UserGroceryCollectionModel response = userGroceryService.saveOrUpdateUserGrocery(userGroceryDto, userUuid);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(ApiUrl.USER_FETCH_ALL_GROCERY)
	public ResponseEntity<List<UserGroceryCollectionModel>> fetchAllUserGroceryCollection(
			@RequestParam(required = true) String userUuid) {
		List<UserGroceryCollectionModel> respose = userGroceryService.userGroceryCollectionsByUserUuid(userUuid);
		return new ResponseEntity<>(respose, HttpStatus.OK);
	}
}
