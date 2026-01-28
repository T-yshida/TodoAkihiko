package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Task;
import com.example.demo.repositry.TaskRepository;
import com.example.demo.service.TaskEditService;

public class TaskEditServiceImpl implements TaskEditService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void taskEdit(int taskid, Task task) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<Task> t = taskRepository.findById((long) taskid);

		Task updateTask = new Task();
		updateTask.setId(t.get().getId());
		updateTask.setTitle(t.get().getTitle());
		updateTask.setPriority(t.get().getPriority());
		updateTask.setDueDate(t.get().getDueDate());

		taskRepository.save(updateTask);
	}

}
