package com.fb.restbucks.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fb.restbucks.exception.InvalidTagException;
import com.fb.restbucks.services.ETagService;

//@Configurable(preConstruction=true,dependencyCheck=true,autowire=Autowire.BY_TYPE)
@Component
@Aspect
public class ResponseEnhancerAspect {

	
	@Autowired
	private ETagService eTagService;
	
	@Around("execution(public org.springframework.http.ResponseEntity com.fb.restbucks.controllers.*.*(..))  && @annotation(javax.ws.rs.POST)")
	public Object addLocation(ProceedingJoinPoint pjp) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object retVal = pjp.proceed();
		ResponseEntity entity = (ResponseEntity)retVal;
		HttpHeaders headers = new HttpHeaders();
		Object id = ReflectionUtils.findMethod(entity.getBody().getClass(), "getId").invoke(entity.getBody());
		headers.add("Location", ServletUriComponentsBuilder.fromRequest(request).path("{id}").build().expand(id).toString());
		return new ResponseEntity(entity.getBody(),headers,entity.getStatusCode());
	}
	
	
	@Around("execution(public org.springframework.http.ResponseEntity com.fb.restbucks.controllers.*.*(..)) && @annotation(javax.ws.rs.GET)")
	public Object addEtag(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object retVal = pjp.proceed();
		ResponseEntity entity = (ResponseEntity)retVal;
		HttpHeaders headers = new HttpHeaders();
		String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
		String tag = null;
		try{
			tag = eTagService.get(url);
		}catch (InvalidTagException e) {
			tag = eTagService.generate(url, entity.getBody());
		}
		headers.add("Etag", tag);
		return new ResponseEntity(entity.getBody(),headers,entity.getStatusCode());
	}
	
}
