package com.iteren.spring_training.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iteren.spring_training.model.HistoryEvent;
import com.iteren.spring_training.model.Task;
import com.iteren.spring_training.service.HistoryService;
import com.iteren.spring_training.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private HistoryService historyService;

	@RequestMapping(method=RequestMethod.GET, path="/tasks", produces={"application/json","application/xml"})
	public List<Task> tasks() {
		return taskService.getTasks();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/task/{id}", produces={"application/json","application/xml"})
	public Task get(@PathVariable("id") Long id) {
		return taskService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/task", consumes={"application/json","application/xml"})
	public void add(@RequestBody Task task) {
		taskService.addTask(task);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/task/{id}", consumes={"application/json","application/xml"})
	public void delete(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/task", consumes={"application/json","application/xml"})
	public void update(@RequestBody Task task) {
		taskService.updateTask(task);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/task/{id}/history", produces={"application/json","application/xml"})
	public List<HistoryEvent> getHistory(@PathVariable("id") Long id) {
		return historyService.getHistoryForTaskId(id);
	}
}
