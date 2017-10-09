package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.SpecificationTypeDAO;
import com.techshop.api.entity.SpecificationType;

@Stateless
public class SpecificationTypeDAOImpl extends GenericDAOImpl<SpecificationType, Long> implements SpecificationTypeDAO, GenericDAO<SpecificationType, Long>  {

}
