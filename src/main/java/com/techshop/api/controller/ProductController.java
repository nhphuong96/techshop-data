package com.techshop.api.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.techshop.api.entity.Product;
import com.techshop.api.model.ProductInformation;
import com.techshop.api.service.ProductService;
import com.techshop.api.util.Result;

@Path("/product")
public class ProductController {

	@EJB
	private ProductService productService;
	
	@POST
	@Path("/save")
	@Produces("application/json; charset=UTF-8")
	public Response save(ProductInformation object) {
		Result<Product> saveResult = productService.saveProduct(object);
		return Response.status(200).entity(saveResult).build();
	}
	
	@POST
	@Path("/deleteById")
	@Produces("application/json; charset=UTF-8")
	public Response deleteById(@QueryParam("id") Long id) {
		Result<Long> deleteResult = productService.deleteProduct(id);
		return Response.status(200).entity(deleteResult).build();
	}
	
	@GET
	@Path("/get")
	@Produces("application/json; charset=UTF-8")
	public Response get(@QueryParam("id") Long id) {
		Result<ProductInformation> getResult = productService.getProduct(id);
		if (getResult.getSuccess() == false) {
			return Response.status(200).entity(getResult).build();
		}
		return Response.status(200).entity(getResult.getData()).build();
	}
	
	@GET
	@Path("/getAll")
	@Produces("application/json; charset=UTF-8")
	public Response getAll() {
		Result<List<ProductInformation>> getAllResult = productService.getAllProduct();
		if (getAllResult.getSuccess() == false) {
			return Response.status(200).entity(getAllResult).build();
		}
		return Response.status(200).entity(getAllResult.getData()).build();
	}
	
}
