package com.fb.restbucks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fb.restbucks.domain.Order;
import com.fb.restbucks.exception.EntityNotFoundException;

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
		Order o = mongoOps.findById(id, Order.class);
		if(o==null){
			throw new EntityNotFoundException();
		}
		return o;
	}

	public void remove(String id) {
		Order o = mongoOps.findById(id, Order.class);
		if(o==null){
			throw new EntityNotFoundException();
		}
		mongoOps.remove(o);		
	}

	public Order update(Order order) {
		Order o = mongoOps.findById(order.getId(), Order.class);
		if(o==null){
			throw new EntityNotFoundException();
		}
		mongoOps.save(order);
		return order;
	}
	
}
