/**
 * 
 */
package com.rohan.grocery_booking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author rrohan419@gmail.com
 */
@Getter
public class UserOnboardingDto {

	@NotBlank
	private String firstName;
	
	private String lastName;
	
	@Email
	@NotNull
	private String email;
	
	private String phoneNumber;
	
	private boolean isAdmin;
	
}
