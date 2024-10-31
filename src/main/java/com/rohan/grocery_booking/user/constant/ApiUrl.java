/**
 * 
 */
package com.rohan.grocery_booking.user.constant;

/**
 * @author rrohan419@gmail.com
 */
public class ApiUrl {

	private ApiUrl() {
	}
	
	public static final String V1_BASE_URL = "m1/v1/";
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String ONBOARD_USER = USER + "/onboard";
	public static final String ADMIN_ADD_GROCERY = ADMIN + "/add-grocery";
	public static final String ADMIN_FETCH_ALL_GROCERY = ADMIN + "/fetch-grocery";
	
}
