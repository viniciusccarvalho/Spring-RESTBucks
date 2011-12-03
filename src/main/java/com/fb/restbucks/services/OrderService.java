package com.fb.restbucks.services;

import com.fb.restbucks.domain.Order;

public interface OrderService {

	Order create(Order order);
	Order find(String id);

}