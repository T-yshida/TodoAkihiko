package com.example.demo.controller;

import java.util.List;
import java.util.Random;

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
import com.example.demo.ririnchan.RirinResources;
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

		List<Task> tasks = taskService.getAllTasks();

		if (q != null && !q.trim().isEmpty()) {
			model.addAttribute("tasks", taskService.searchTasks(q));
		} else {
			model.addAttribute("tasks", tasks);
		}

		long total = tasks.size();
		long completed = tasks.stream()
				.filter(Task::isCompleted)
				.count();

		int progress = total == 0
				? 0
				: (int) Math.round((completed * 100.0) / total);

		model.addAttribute("q", q);
		model.addAttribute("genres", genreService.getAllGenre());
		model.addAttribute("priorities", Priority.values());
		model.addAttribute("newTask", new Task());
		model.addAttribute("newGenre", new GenreDto());
		model.addAttribute("progress", progress);
		model.addAttribute("completedCount", completed);
		model.addAttribute("totalCount", total);

		Random ran = new Random();

		String message;
		String image;
		String voice;

		int ranNum = 0;

		if (progress == 100) {
			ranNum = ran.nextInt(5);
			message = RirinResources.message[3][ranNum];
			image = RirinResources.portrait[ran.nextInt(7)];
			voice = RirinResources.voice[3][ranNum];
		} else if (progress >= 99) {
			ranNum = ran.nextInt(5);
			message = RirinResources.message[2][ranNum];
			image = RirinResources.portrait[ran.nextInt(7)];
			voice = RirinResources.voice[2][ranNum];
		} else if (progress >= 66) {
			ranNum = ran.nextInt(5);
			message = RirinResources.message[1][ranNum];
			image = RirinResources.portrait[ran.nextInt(7)];
			voice = RirinResources.voice[1][ranNum];
		} else {
			ranNum = ran.nextInt(6);
			message = RirinResources.message[0][ranNum];
			image = RirinResources.portrait[ran.nextInt(7)];
			voice = RirinResources.voice[0][ranNum];
		}

		model.addAttribute("progress", progress);
		model.addAttribute("message", message);
		model.addAttribute("image", image);
		model.addAttribute("voice", voice);

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
