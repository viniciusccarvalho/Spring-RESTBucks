package com.fb.restbucks;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fb.restbucks.config.AppConfig;
import com.fb.restbucks.domain.Order;
import com.fb.restbucks.domain.OrderStatus;
import com.fb.restbucks.exception.InvalidTagException;
import com.fb.restbucks.services.ETagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class ETagServiceTest {

	@Autowired
	private ETagService service;
	
	
	@Test
	public void testCreateTag() throws Exception{
		Order order = new Order();
		String url = "http://localhost:8080/restbucks/orders/abc";
		order.setId("abc");
		order.setStatus(OrderStatus.PENDING);
		String tag = service.generate(url, order);
		System.out.println(tag);
		service.remove(url);
	}
	
	@Test(expected=InvalidTagException.class) 
	public void testInvalidTag() throws Exception{
		String tag = service.get("http://neverfound.com");
	}
	
	
	@Test
	public void updateTag() throws Exception {
		Order order = new Order();
		String url = "http://localhost:8080/restbucks/orders/abc";
		order.setId("abc");
		order.setStatus(OrderStatus.PENDING);
		String tag = service.generate(url, order);
		order.setStatus(OrderStatus.SERVED);
		String newTag = service.update(url, order);
		String check = service.get(url);
		Assert.assertEquals(newTag, check);
		service.remove(url);
	}
}
