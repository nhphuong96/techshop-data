package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.ProductDAO;
import com.techshop.api.entity.Product;

@Stateless
public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements ProductDAO, GenericDAO<Product, Long>  {

}
