package com.techshop.api.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_order")
@JsonIgnoreProperties( value = { "orderDetails"})
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String status;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "orderId")
	private List<OrderDetail> orderDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
}
