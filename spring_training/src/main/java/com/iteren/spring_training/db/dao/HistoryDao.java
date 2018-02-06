package com.iteren.spring_training.db.dao;

import java.util.List;

import com.iteren.spring_training.model.HistoryEvent;
import com.iteren.spring_training.model.Task;

public interface HistoryDao {

	List<HistoryEvent> getHistoryForTask(Task task);
	
	List<HistoryEvent> getHistoryForTaskId(long id);
	
	void save(HistoryEvent event);

}
