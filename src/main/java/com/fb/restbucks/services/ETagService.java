package com.fb.restbucks.services;

public interface ETagService {
	
	public String generate(String url, Object entity);
	public void remove(String url);
	public String get(String url);
	public String update(String url, Object entity);
}
