package com.fb.restbucks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fb.restbucks.exception.EntityNotFoundException;

public class BaseController {

	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity entityNotFound(){
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
}
