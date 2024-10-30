/**
 * 
 */
package com.rohan.grocery_booking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.grocery_booking.user.entity.UserRole;

/**
 * @author rohan.shrivastava@mindbowser.com
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
