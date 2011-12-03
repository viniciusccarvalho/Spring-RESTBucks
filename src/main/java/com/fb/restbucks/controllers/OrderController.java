package com.fb.restbucks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fb.restbucks.domain.Order;
import com.fb.restbucks.services.OrderService;

@Controller
@RequestMapping("/orders/*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.POST, value="/")
	@POST
	public ResponseEntity<Order> dynamicTest(@RequestBody Order order){
		orderService.create(order);
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
	}
	
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.GET, value="/{id}")
	@GET
	public ResponseEntity<Order> getOrder(@PathVariable("id") String id){
		Order order = orderService.find(id);
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}
	
}
