package com.iteren.spring_training.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iteren.spring_training.model.HistoryEvent;
import com.iteren.spring_training.model.Task;

@Component("historyDao")
public class HistoryDaoImpl implements HistoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HistoryEvent> getHistoryForTask(Task task) {
		return getHistoryForTaskId(task.getId());
	}

	@Override
	public List<HistoryEvent> getHistoryForTaskId(long id) {
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<HistoryEvent> history = session.createQuery("from HistoryEvent where taskId = " + id).list();
		session.close();
		return history;
	}

	@Override
	public void save(HistoryEvent event) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(event);
		tx.commit();
		session.close();
	}

}
