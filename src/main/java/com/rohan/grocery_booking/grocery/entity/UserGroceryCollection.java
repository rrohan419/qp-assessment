/**
 * 
 */
package com.rohan.grocery_booking.grocery.entity;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Entity
@Table(name = "user_grocery_collections")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroceryCollection extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String uuid;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String userUuid;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<UserGroceryList> userGroceryLists;
}

