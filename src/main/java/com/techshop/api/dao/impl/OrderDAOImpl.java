package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.OrderDAO;
import com.techshop.api.entity.Order;

@Stateless
public class OrderDAOImpl extends GenericDAOImpl<Order, Long> implements OrderDAO, GenericDAO<Order, Long>{

}
