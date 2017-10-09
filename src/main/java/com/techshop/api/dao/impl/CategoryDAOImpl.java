package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.CategoryDAO;
import com.techshop.api.dao.GenericDAO;
import com.techshop.api.entity.Category;

@Stateless
public class CategoryDAOImpl extends GenericDAOImpl<Category, Long> implements CategoryDAO, GenericDAO<Category, Long>  {

}
