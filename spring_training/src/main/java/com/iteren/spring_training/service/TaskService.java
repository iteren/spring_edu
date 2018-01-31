package com.iteren.spring_training.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.iteren.spring_training.db.dao.TaskDao;
import com.iteren.spring_training.model.Task;

@Service("service")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
@Lazy
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public TaskService() {
		System.out.println("TaskService.initialized!");
	}

	public List<Task> getTasks() {
		return taskDao.list();
	}
	
	public void addTask(Task task) {
		taskDao.save(task);
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
