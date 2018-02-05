package com.iteren.spring_training.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j;

@Aspect
@Configuration
@Log4j
public class HistoryService {
	@After("execution(* com.iteren.spring_training.service.TaskService.*(..))")
	public void before(JoinPoint joinPoint){
		log.info(" before ");
		log.info(String.format(" Allowed execution for %s", joinPoint));
	}
}
