package com.fb.restbucks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan(basePackages={"com.fb.restbucks.services","com.fb.restbucks.aspects"},excludeFilters={@Filter(Configuration.class)})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo(), "restbucks");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate template = new MongoTemplate(mongoDbFactory());
		template.setWriteConcern(WriteConcern.SAFE);
		return template;
	}

}
