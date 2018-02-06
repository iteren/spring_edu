package com.iteren.spring_training.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iteren.spring_training.model.Task;

@Component("taskDao")
public class TaskDaoImpl implements TaskDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Task task) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(task);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> list() {
		Session session = this.sessionFactory.openSession();
		List<Task> tasks = session.createQuery("from Task").list();
		session.close();
		return tasks;
	}

	@Override
	public void update(Task task) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(task);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Task task) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(task);
		tx.commit();
		session.close();
	}

	@Override
	public Task get(Long id) {
		Session session = this.sessionFactory.openSession();
		Task task = session.get(Task.class, id);
		session.close();
		return task;
	}
}
