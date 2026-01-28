package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repositry.TaskSortRepository;
import com.example.demo.service.TaskSortService;

@Service
public class TaskSortServiceImpl implements TaskSortService {

	@Autowired
	private TaskSortRepository taskSortRepository;

	@Override
	public List<Task> getSortedTasks(String type) {

		if (type == null) {
			return taskSortRepository.findAllByOrderByCreatedAtDesc();
		}

		switch (type) {
		case "priority":
			return taskSortRepository.findAllByOrderByPriorityDesc();

		case "completed":
			return taskSortRepository.findAllByOrderByCompletedAsc();

		case "createdAt":
		default:
			return taskSortRepository.findAllByOrderByCreatedAtDesc();
		}
	}
}
