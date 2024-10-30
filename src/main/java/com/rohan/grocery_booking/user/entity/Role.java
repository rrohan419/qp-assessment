/**
 * 
 */
package com.rohan.grocery_booking.user.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;
import com.rohan.grocery_booking.common.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Entity
@Table(name = "roles")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String uuid;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private UserType userType;
}
