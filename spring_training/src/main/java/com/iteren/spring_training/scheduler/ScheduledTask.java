package com.iteren.spring_training.scheduler;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.iteren.spring_training.model.Task;
import com.iteren.spring_training.service.TaskService;

public class ScheduledTask implements Runnable {
	public static final String EXECUTED_TASKS = "executed_tasks";
	private Task task;
	private TaskService taskService;
	private HttpSession session;
	
	public ScheduledTask(HttpSession session, TaskService taskService, Task task) {
		this.task = task;
		this.taskService = taskService;
		this.session = session;
	}

	@Override
	public void run() {
		task.setCompleated(true);
		taskService.updateTask(task);
		storeExecutedTask(task);
	}
	
	@SuppressWarnings("unchecked")
	private void storeExecutedTask(Task task) {
		if(session.getAttribute(EXECUTED_TASKS) == null) {
			session.setAttribute(EXECUTED_TASKS, new ArrayList<>());
		}
		((ArrayList<Task>)session.getAttribute(EXECUTED_TASKS)).add(task);
	}
}
