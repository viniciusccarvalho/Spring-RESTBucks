package com.fb.restbucks.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan(basePackages={"com.fb.restbucks.services"},excludeFilters={@Filter(Configuration.class)})
@EnableCaching
public class AppConfig {
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo(), "restbucks");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate template = new MongoTemplate(mongoDbFactory());
		template.setWriteConcern(WriteConcern.SAFE);
		return template;
	}
	
	@Bean
	public CacheManager cacheManager() throws Exception {
		SimpleCacheManager simpleCache = new SimpleCacheManager();
		simpleCache.setCaches(Arrays.asList(new ConcurrentMapCache("tags")));
		return simpleCache;
	}

}
