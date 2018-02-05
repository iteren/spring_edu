package com.iteren.spring_training.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {

	@Id
	@NotNull
	@Min(value = 1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 4, max = 15, message = "Wrong task name, name should be at least 4 characters and 15 characters maximum!")
	private String name;
	
	@NotNull
	@Size(min = 5, max = 255, message = "Wrong task description, description should be at least 5 characters and 255 characters maximum!")
	private String description;
	
	@NotNull
	@Future(message = "DueDate should be in the future!")
	private Date dueDate;
	
	private boolean compleated;
}
