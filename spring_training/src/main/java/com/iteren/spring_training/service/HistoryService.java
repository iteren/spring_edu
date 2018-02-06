package com.iteren.spring_training.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.iteren.spring_training.db.dao.HistoryDao;
import com.iteren.spring_training.model.HistoryEvent;
import com.iteren.spring_training.model.Task;

@Service("historyService")
@Aspect
@Configuration
public class HistoryService {
	
	@Autowired
	private HistoryDao historyDao;
	
	@After("execution(* com.iteren.spring_training.service.TaskService.addTask(..)) && args(task)")
	public void afterAddTask(JoinPoint joinPoint, Task task) {
		createHistoryEvent(task.getId(), "task added", "Task name: " + task.getName(), null);
	}
	
	@AfterReturning(pointcut = "execution(* com.iteren.spring_training.service.TaskService.deleteTask(..)) && args(id)", 
			returning = "deletedTask")
	public void afterDeleteTask(JoinPoint joinPoint, Long id, Task deletedTask) {
		createHistoryEvent(id, "task deleted", "Task name: " + deletedTask.getName(), null);
	}

	@AfterReturning(pointcut = "execution(* com.iteren.spring_training.service.TaskService.updateTask(..)) && args(newTask)",
			returning = "oldTask")
	public void afterUpdateTask(JoinPoint joinPoint, Task newTask, Task oldTask) {
		if(!newTask.getName().equalsIgnoreCase(oldTask.getName())) {
			createHistoryEvent(newTask.getId(), "name", newTask.getName(), oldTask.getName());
		}
		
		if(!newTask.getDescription().equalsIgnoreCase(oldTask.getDescription())) {
			createHistoryEvent(newTask.getId(), "description", newTask.getDescription(), oldTask.getDescription());
		}
		
		if(!newTask.getDueDate().equals(oldTask.getDueDate())) {
			createHistoryEvent(newTask.getId(), "dueDate", newTask.getDueDate(), oldTask.getDueDate());
		}
		
		if(newTask.isCompleated() != oldTask.isCompleated()) {
			createHistoryEvent(newTask.getId(), "compleated", newTask.isCompleated(), oldTask.isCompleated());
		}
	}
	
	public List<HistoryEvent> getHistoryForTaskId(long id) {
		return historyDao.getHistoryForTaskId(id);
	}
	
	private void createHistoryEvent(Long id, String changedField, Object newValue, Object oldValue) {
		HistoryEvent event = new HistoryEvent();
		event.setTaskId(id);
		event.setChangedField(changedField);
		event.setNewValue(newValue.toString());
		event.setOldValue(oldValue.toString());
		historyDao.save(event );
	}
}
