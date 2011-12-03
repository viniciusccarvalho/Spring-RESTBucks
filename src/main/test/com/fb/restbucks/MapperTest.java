package com.fb.restbucks;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import com.fb.restbucks.domain.Item;
import com.fb.restbucks.domain.Milk;
import com.fb.restbucks.domain.Order;
import com.fb.restbucks.domain.OrderStatus;
import com.fb.restbucks.domain.Size;

public class MapperTest {

	@Test
	public void testMapper() throws Exception {
		Order order = new Order();
		order.setId("abc");
		order.setStatus(OrderStatus.PENDING);
		Item item = new Item();
		item.setMilk(Milk.WHOLE);
		item.setQuantity(1);
		item.setSize(Size.LARGE);
		order.addItem(item);
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, order);
		System.out.println(writer.toString());
		
	}

}
