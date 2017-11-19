package com.techshop.api.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.techshop.api.dao.OrderDAO;
import com.techshop.api.entity.Order;
import com.techshop.api.util.EntityResult;
import com.techshop.api.util.Result;

@Path("/order")
public class OrderController {
	@EJB
	private OrderDAO orderDAO;
	
	@POST
	@Path("/save")
	@Produces("application/json; charset=UTF-8")
	public Response save(Order object) {
		Result<Order> saveResult = orderDAO.save(object);
		EntityResult result = new EntityResult(saveResult.getData().getId(), "success", true);
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/deleteById")
	@Produces("application/json; charset=UTF-8")
	public Response deleteById(@QueryParam("id") Long id) {
		Result<Long> deleteResult = orderDAO.delete(id);
		return Response.status(200).entity(deleteResult).build();
	}
	
	@GET
	@Path("/get")
	@Produces("application/json; charset=UTF-8")
	public Response get(@QueryParam("id") Long id) {
		Result<Order> getResult = orderDAO.findOne(id);
		if (getResult.getSuccess() == false) {
			return Response.status(200).entity(getResult).build();
		}
		return Response.status(200).entity(getResult.getData()).build();
	}
	
	@GET
	@Path("/getAll")
	@Produces("application/json; charset=UTF-8")
	public Response getAll() {
		Result<List<Order>> getAllResult = orderDAO.findAll();
		if (getAllResult.getSuccess() == false) {
			return Response.status(200).entity(getAllResult).build();
		}
		return Response.status(200).entity(getAllResult.getData()).build();
	}
	
	@POST
	@Path("/update")
	@Produces("application/json; charset=UTF-8")
	public Response update(Order order) {
		if (order.getId() == null) {
			return Response.status(200).entity(new Result<>(null, "Primary key is required", false)).build();
		}
		Result<Order> updateResult = orderDAO.update(order);
		return Response.status(200).entity(updateResult).build();
	}
}
