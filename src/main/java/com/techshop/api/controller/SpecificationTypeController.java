package com.techshop.api.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.techshop.api.dao.SpecificationTypeDAO;
import com.techshop.api.entity.SpecificationType;
import com.techshop.api.util.Result;

@Path("/specificationtype")
public class SpecificationTypeController {

	@EJB
	private SpecificationTypeDAO specificationTypeDao;

	@GET
	@Path("/get")
	@Produces("application/json; charset=UTF-8")
	public Response get(@QueryParam("id") Long id) {
		
		if (id == null) {
			return Response.status(200).entity(new Result<>("Value 'id' is required", false)).build();
		}
		
		Result<SpecificationType> findResult = specificationTypeDao.findOne(id);
		
		if (findResult.getSuccess() == false) {
			return Response.status(200).entity(findResult).build();
		}
		
		return Response.status(200).entity(findResult.getData()).build();
	}
	
	@GET
	@Path("/getAll")
	@Produces("application/json; charset=UTF-8")
	public Response getAll() {
		Result<List<SpecificationType>> resultGetAll = specificationTypeDao.findAll();
		if (resultGetAll.getSuccess() == false) {
			return Response.status(200).entity(resultGetAll).build();
		}
		List<SpecificationType> categories = specificationTypeDao.findAll().getData();
		
		return Response.status(200).entity(categories).build();
	}
	
	@POST
	@Path("/save")
	@Produces("application/json; charset=UTF-8")
	public Response save(SpecificationType object) {
		if (object.getId() != null) {
			return Response.status(200).entity(new Result<>(null, "Conflict primary key", false)).build();
		}
		Result<SpecificationType> saveResult = specificationTypeDao.save(object);
		return Response.status(200).entity(saveResult).build();
	}
	
	@POST
	@Path("/deleteById")
	@Produces("application/json; charset=UTF-8")
	public Response deleteById(@QueryParam("id") Long id) {
		Result<Long> saveResult = specificationTypeDao.delete(id);
		return Response.status(200).entity(saveResult).build();
	}

	@POST
	@Path("/update")
	@Produces("application/json; charset=UTF-8")
	public Response update(SpecificationType category) {
		if (category.getId() == null) {
			return Response.status(200).entity(new Result<>(null, "Primary key is required", false)).build();
		}
		Result<SpecificationType> updateResult = specificationTypeDao.update(category);
		return Response.status(200).entity(updateResult).build();
	}
}
