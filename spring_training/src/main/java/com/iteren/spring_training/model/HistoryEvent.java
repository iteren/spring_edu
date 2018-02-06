package com.iteren.spring_training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "task_history")
public class HistoryEvent {
	@Id
	@NotNull
	@Min(value = 1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Long taskId;
	
	@NotNull
	private String changedField;
	
	private String oldValue;
	
	private String newValue;
}
