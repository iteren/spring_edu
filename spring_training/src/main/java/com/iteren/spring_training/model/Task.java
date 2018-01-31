package com.iteren.spring_training.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Task")
public class Task {

	@Id
	private String name;
	private String description;
	private Date dueDate;
}
