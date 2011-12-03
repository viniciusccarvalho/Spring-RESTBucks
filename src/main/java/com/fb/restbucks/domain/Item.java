package com.fb.restbucks.domain;

public class Item {
	private Milk milk;
	private String name;
	private int quantity;
	private Size size;
	
	public Milk getMilk() {
		return milk;
	}
	public void setMilk(Milk milk) {
		this.milk = milk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
}
