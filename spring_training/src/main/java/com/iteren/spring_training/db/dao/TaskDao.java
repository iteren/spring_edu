package com.iteren.spring_training.db.dao;

import java.util.List;

import com.iteren.spring_training.model.Task;

public interface TaskDao {

	void save(Task task);

	List<Task> list();

}
