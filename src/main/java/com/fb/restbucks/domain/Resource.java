package com.fb.restbucks.domain;

import java.util.LinkedList;
import java.util.List;

public class Resource<T> {

	private T entity;
	private List<Link> links;
	
	public Resource(T entity){
		this.entity = entity;
		this.links  = new LinkedList<Link>();
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
	public void addLink(Link link){
		this.links.add(link);
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}
