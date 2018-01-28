package com.iteren.spring_training.model;

import java.util.Date;

import lombok.Data;

@Data
public class Task {

	private String name;
	private Date dueDate;
}
