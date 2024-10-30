/**
 * 
 */
package com.rohan.grocery_booking.user.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Entity
@Table(name = "user_roles")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserRole extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "role_uuid", referencedColumnName = "uuid", nullable = false)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
	private User user;
}
