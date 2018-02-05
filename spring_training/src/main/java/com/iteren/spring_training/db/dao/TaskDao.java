package com.iteren.spring_training.db.dao;

import java.util.List;

import com.iteren.spring_training.model.Task;

public interface TaskDao {

	List<Task> list();
	
	Task get(Long id);

	void save(Task task);

	void update(Task task);

	void delete(Task task);
}
