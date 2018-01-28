package com.iteren.spring_training.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("service")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
@Lazy
public class TaskService {

	public TaskService() {
		System.out.println("TaskService.initialized!");
	}

	public String getMessage() {
		return "Hello from spring boot!";
	}
	
	@PostConstruct
	public void postC() {
		System.out.println("TaskService.created!");
	}

	@PreDestroy
	public void preD() {
		System.out.println("TaskService.toBeDeleted!");
	}
}
