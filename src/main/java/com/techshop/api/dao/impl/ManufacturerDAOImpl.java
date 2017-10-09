package com.techshop.api.dao.impl;

import javax.ejb.Stateless;

import com.techshop.api.dao.GenericDAO;
import com.techshop.api.dao.ManufacturerDAO;
import com.techshop.api.entity.Manufacturer;

@Stateless
public class ManufacturerDAOImpl extends GenericDAOImpl<Manufacturer, Long> implements ManufacturerDAO, GenericDAO<Manufacturer, Long>  {

}
