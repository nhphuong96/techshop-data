package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.PaymentDAO;
import com.techshop.api.entity.Payment;

@Stateless
public class PaymentDAOImpl extends GenericDAOImpl<Payment, Long> implements PaymentDAO, GenericDAO<Payment, Long>  {

}
