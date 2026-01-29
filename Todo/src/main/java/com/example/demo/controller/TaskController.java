package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.Priority; // 追加
import com.example.demo.entity.Task;
import com.example.demo.service.GenreService;
import com.example.demo.service.TaskService;

@Controller
public class TaskController {

	// TaskServiceを注入することで、タスクの操作（保存・取得・削除）を簡単に実行できる
	@Autowired
	private TaskService taskService;
	@Autowired
	private GenreService genreService;

	/**
	 * タスク一覧を表示するエンドポイント
	 * @param model Thymeleafでデータを表示するためのModelオブジェクト
	 * @return タスク一覧画面（index.html）
	 */
	@GetMapping("/")
	public String index(@RequestParam(name = "q", required = false) String q, Model model) {

		if (q != null && !q.trim().isEmpty()) {
			model.addAttribute("tasks", taskService.searchTasks(q));
		} else {
			model.addAttribute("tasks", taskService.getAllTasks());
		}

		model.addAttribute("q", q);
		model.addAttribute("genres", genreService.getAllGenre());
		model.addAttribute("priorities", Priority.values());
		model.addAttribute("newTask", new Task());
		model.addAttribute("newGenre", new GenreDto());

		return "index";
	}

	/**
	 * 新しいタスクを追加するエンドポイント
	 * @param newTask ユーザーがフォームで入力したタスクデータ
	 * @return タスク一覧ページにリダイレクト
	 */
	@PostMapping("/add")
	public String addTask(@ModelAttribute Task newTask) {
		// フォームから送信されたタスクを保存
		taskService.saveTask(newTask);
		// タスク一覧ページにリダイレクト（再読み込み）
		return "redirect:/";
	}

	/**
	 * タスクを削除するエンドポイント
	 * @param id 削除するタスクのID
	 * @return タスク一覧ページにリダイレクト
	 */
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		// 指定されたIDのタスクを削除
		taskService.deleteTask(id);
		// タスク一覧ページにリダイレクト（再読み込み）
		return "redirect:/";
	}

	@PostMapping("/complete/{id}")
	public String toggleComplete(@PathVariable Long id) {
		taskService.toggleCompleted(id);
		return "redirect:/";
	}

	@PostMapping("/reset-all")
	public String resetAllTasks() {
		taskService.resetCompletedDaily();
		return "redirect:/";
	}

	@GetMapping("/api/tasks/today/incomplete")
	@ResponseBody
	public List<Task> getTodayIncompleteTasks() {
		return taskService.getTodayIncompleteTasks();
	}

}
