/**
 * 
 */
package com.rohan.grocery_booking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.common.enums.UserType;
import com.rohan.grocery_booking.user.entity.Role;

/**
 * @author rohan.shrivastava@mindbowser.com
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByUserType(UserType userType);
}
