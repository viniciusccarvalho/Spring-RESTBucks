package com.fb.restbucks.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
	
	private String id;
	private OrderStatus status;
	private List<Item> items = new ArrayList<Item>();
	
	public void addItem(Item item){
		this.items.add(item);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
