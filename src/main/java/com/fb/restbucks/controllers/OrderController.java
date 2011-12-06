package com.fb.restbucks.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fb.restbucks.domain.Order;
import com.fb.restbucks.domain.Resource;
import com.fb.restbucks.exception.InvalidTagException;
import com.fb.restbucks.services.OrderService;

@Controller
@RequestMapping("/orders/*")
public class OrderController extends BaseController<Resource<Order>>{

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.POST, value="/")
	public ResponseEntity<Resource<Order>> dynamicTest(@RequestBody Order order){
		orderService.create(order);
		Resource<Order> resource = new Resource<Order>(order);
		return new ResponseEntity<Resource<Order>>(resource,HttpStatus.CREATED);
	}
	
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Resource<Order>> getOrder(@PathVariable("id") String id){
		Order order = orderService.find(id);
		Resource<Order> resource = new Resource<Order>(order);
		return new ResponseEntity<Resource<Order>>(resource,HttpStatus.OK);
	}
	
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Resource<Order>> update(@RequestBody Order order){
		order = orderService.update(order);
		Resource<Order> resource = new Resource<Order>(order);
		return new ResponseEntity<Resource<Order>>(resource,HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Resource<Order>> describe(HttpServletRequest request) {
		//TODO describe this resource links
		return null;
	}
	
	

	
}
