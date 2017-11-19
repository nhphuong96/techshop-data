package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.OrderDetailDAO;
import com.techshop.api.entity.OrderDetail;

@Stateless
public class OrderDetailDAOImpl extends GenericDAOImpl<OrderDetail, Long> implements OrderDetailDAO, GenericDAO<OrderDetail, Long>{

}
