package com.iteren.spring_training.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteren.spring_training.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService s;

	@RequestMapping("/")
	public String index() {
		return s.getMessage();
	}
}
