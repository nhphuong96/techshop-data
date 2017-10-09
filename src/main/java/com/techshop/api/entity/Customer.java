package com.techshop.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullname;
	
	private String email;
	
	private String address;
	
	private String city;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private String company;
	
	private String username;
	
	private String password;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private Set<Order> orders;
}
