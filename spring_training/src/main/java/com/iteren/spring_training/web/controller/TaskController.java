package com.iteren.spring_training.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iteren.spring_training.model.HistoryEvent;
import com.iteren.spring_training.model.Task;
import com.iteren.spring_training.scheduler.ScheduledTask;
import com.iteren.spring_training.service.HistoryService;
import com.iteren.spring_training.service.TaskService;
import com.iteren.spring_training.web.controller.exception.ExceptionResolver;

@RestController
@SessionAttributes({ExceptionResolver.EXCEPTIONS, ScheduledTask.EXECUTED_TASKS})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskScheduler scheduler;
	
	@RequestMapping(method=RequestMethod.GET, path="/tasks", produces={"application/json","application/xml"})
	public List<Task> tasks() {
		return taskService.getTasks();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/task/{id}", produces={"application/json","application/xml"})
	public Task get(@PathVariable("id") Long id) {
		return taskService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/task", consumes={"application/json","application/xml"})
	public void add(@RequestBody Task task, HttpSession httpSession) {
		taskService.addTask(task);
		scheduler.schedule(new ScheduledTask(httpSession, taskService, task), task.getDueDate());
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
	
	@RequestMapping(method=RequestMethod.GET, path="/exceptions", produces={"application/json","application/xml"})
	public List<Exception> getExceptions(@ModelAttribute(ExceptionResolver.EXCEPTIONS) List<Exception> exceptions) {
		return exceptions;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/executed", produces={"application/json","application/xml"})
	public List<Task> getExecutedTasks(@ModelAttribute(ScheduledTask.EXECUTED_TASKS) List<Task> tasks) {
		return tasks;
	}
	
	@ModelAttribute(ExceptionResolver.EXCEPTIONS)
    public List<Exception> getEmptyExceptionsList() {
        return new ArrayList<>();
    }  
	
	@ModelAttribute(ScheduledTask.EXECUTED_TASKS)
    public List<Task> getEmptyExecutedTasks() {
        return new ArrayList<>();
    } 
}
