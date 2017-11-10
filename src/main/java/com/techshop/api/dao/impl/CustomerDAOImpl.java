package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.CustomerDAO;
import com.techshop.api.dao.GenericDAO;
import com.techshop.api.entity.Customer;

@Stateless
public class CustomerDAOImpl extends GenericDAOImpl<Customer, Long> implements CustomerDAO, GenericDAO<Customer, Long>  {

}
