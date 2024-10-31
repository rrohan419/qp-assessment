/**
 * 
 */
package com.rohan.grocery_booking.grocery.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author rrohan419@gmail.com
 */
@Entity
@Table(name = "user_grocery_lists")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserGroceryList extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String uuid;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private MasterGroceryList masterGroceryList;
	
	
}
