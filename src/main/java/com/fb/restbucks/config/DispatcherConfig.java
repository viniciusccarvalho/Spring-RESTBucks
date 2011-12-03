package com.fb.restbucks.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.fb.restbucks.controllers"},excludeFilters={@Filter(Configuration.class)})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DispatcherConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		List<MediaType> mediatypes = new ArrayList<MediaType>();
		mediatypes.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(mediatypes);
		converters.add(converter);
	}

	
	@Bean
	public DefaultAnnotationHandlerMapping handlerMapping(){
		return new DefaultAnnotationHandlerMapping();
	}
}
