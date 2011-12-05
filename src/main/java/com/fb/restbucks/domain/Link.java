package com.fb.restbucks.domain;

import org.springframework.http.HttpMethod;

public class Link {
	
	private String rel;
	private String href;
	private HttpMethod method;
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
}
