/**
 * 
 */
package com.rohan.grocery_booking.grocery.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.rohan.grocery_booking.common.entity.BaseEntity;
import com.rohan.grocery_booking.common.enums.EntityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "master_grocery_lists")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterGroceryList extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private EntityStatus entityStatus = EntityStatus.ACTIVE;
	
	@Column(nullable = false, unique = true)
	private String uuid;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column
	private String updatedBy;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String currency;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private String quantityUnit;
	
}
