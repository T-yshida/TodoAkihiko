package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Priority;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskSortService;

@Controller
public class SortingController {

	@Autowired
	private TaskSortService taskSortService;

	@GetMapping("/sort")
	public String sort(
			@RequestParam String type,
			Model model) {

		// 並び替え済みタスク
		model.addAttribute("tasks", taskSortService.getSortedTasks(type));

		// フォーム用
		model.addAttribute("newTask", new Task());
		model.addAttribute("priorities", Priority.values());

		return "index";
	}
}
