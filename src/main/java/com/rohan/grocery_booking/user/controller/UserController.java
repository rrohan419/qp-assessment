/**
 * 
 */
package com.rohan.grocery_booking.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.grocery_booking.user.constant.ApiUrl;
import com.rohan.grocery_booking.user.dto.UserOnboardingDto;
import com.rohan.grocery_booking.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@RestController
@RequestMapping(ApiUrl.V1_BASE_URL)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping(ApiUrl.ONBOARD_USER)
	public ResponseEntity<String> signupUser(@RequestBody @Valid UserOnboardingDto userOnboardingDto) {
		userService.onboardUser(userOnboardingDto);
		 return new ResponseEntity<>("User onboarded successfully", HttpStatus.CREATED);
	}
}
