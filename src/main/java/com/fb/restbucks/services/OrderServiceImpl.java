package com.fb.restbucks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fb.restbucks.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private MongoOperations mongoOps;
	
	/* (non-Javadoc)
	 * @see com.fb.restbucks.services.OrderService#create(com.fb.restbucks.domain.Order)
	 */
	public Order create(Order order){
		mongoOps.save(order);
		return order;
	}

	public Order find(String id) {
		return mongoOps.findById(id, Order.class);
	}
	
}
