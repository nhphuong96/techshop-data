package com.techshop.api.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ship_date")
	private LocalDate shipDate;
	
	@Column(name = "ship_cost")
	private Integer shipCost;
	
	@Column(name = "discount_code")
	private String discountCode;
	
	private String status;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	@Column(name = "total_price")
	private Integer totalPrice;
	
	@OneToOne(mappedBy="payment")
	private Order order;
}
