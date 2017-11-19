package com.techshop.api.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.techshop.api.dao.PaymentDAO;
import com.techshop.api.entity.Payment;
import com.techshop.api.util.Result;

@Path("/payment")
public class PaymentController {
	@EJB
	private PaymentDAO paymentDAO;
	
	@POST
	@Path("/save")
	@Produces("application/json; charset=UTF-8")
	public Response save(Payment object) {
		Result<Payment> saveResult = paymentDAO.save(object);
		return Response.status(200).entity(saveResult).build();
	}
	
	@POST
	@Path("/deleteById")
	@Produces("application/json; charset=UTF-8")
	public Response deleteById(@QueryParam("id") Long id) {
		Result<Long> deleteResult = paymentDAO.delete(id);
		return Response.status(200).entity(deleteResult).build();
	}
	
	@GET
	@Path("/get")
	@Produces("application/json; charset=UTF-8")
	public Response get(@QueryParam("id") Long id) {
		Result<Payment> getResult = paymentDAO.findOne(id);
		if (getResult.getSuccess() == false) {
			return Response.status(200).entity(getResult).build();
		}
		return Response.status(200).entity(getResult.getData()).build();
	}
	
	@GET
	@Path("/getAll")
	@Produces("application/json; charset=UTF-8")
	public Response getAll() {
		Result<List<Payment>> getAllResult = paymentDAO.findAll();
		if (getAllResult.getSuccess() == false) {
			return Response.status(200).entity(getAllResult).build();
		}
		return Response.status(200).entity(getAllResult.getData()).build();
	}
	
	@POST
	@Path("/update")
	@Produces("application/json; charset=UTF-8")
	public Response update(Payment order) {
		if (order.getId() == null) {
			return Response.status(200).entity(new Result<>(null, "Primary key is required", false)).build();
		}
		Result<Payment> updateResult = paymentDAO.update(order);
		return Response.status(200).entity(updateResult).build();
	}
}
