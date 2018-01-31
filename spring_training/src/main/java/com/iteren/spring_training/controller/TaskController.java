package com.iteren.spring_training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iteren.spring_training.model.Task;
import com.iteren.spring_training.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(method=RequestMethod.GET, path="/tasks")
	public List<Task> tasks() {
		return taskService.getTasks();
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/tasks/add", consumes="application/json")
	public void add(@RequestBody Task task) {
		taskService.addTask(task);
	}
}
