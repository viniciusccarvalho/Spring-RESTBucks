package com.fb.restbucks.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface ETagService {
	
	@Cacheable(value="tags", key="#url")
	public String generate(String url, Object entity);
	
	@CacheEvict(value="tags",key="#url")
	public void remove(String url);
	
	@Cacheable(value="tags", key="#url")
	public String get(String url);
	
	@CacheEvict(value="tags",key="#url")
	public String update(String url, Object entity);
}
