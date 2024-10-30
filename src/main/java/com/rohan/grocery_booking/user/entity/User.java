/**
 * 
 */
package com.rohan.grocery_booking.user.entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;
import com.rohan.grocery_booking.common.enums.EntityStatus;
import com.rohan.grocery_booking.common.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rrohan419@gmail.com
 */
@Entity
@Table(name = "users")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EntityStatus entityStatus = EntityStatus.ACTIVE;
	
	@Column(nullable = false, unique = true)
	private String uuid;
	
	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@Column
	private String phoneNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<UserRole> userRoles;
	
	/**
	 * Converts the user roles to a Set of UserType.
	 * 
	 * @author rrohan419@gmail.com
	 *
	 * @return {@link Set}
	 * @see UserType
	 */
	public Set<UserType> getUserTypeSet() {
		if (userRoles == null || userRoles.isEmpty()) {
			return Collections.emptySet();
		}

		return userRoles.stream()
				.map(userRole -> userRole.getRole().getUserType())
				.collect(Collectors.toSet());
	}
}
