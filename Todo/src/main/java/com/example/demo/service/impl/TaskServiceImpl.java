package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repositry.TaskRepository;
import com.example.demo.service.TaskService;

/**
 * タスク操作のビジネスロジックを実装するサービスクラス
 * TaskServiceインターフェースを実装しています。
 */
@Service
public class TaskServiceImpl implements TaskService {

	// TaskRepositoryを注入してデータベース操作を簡略化
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * すべてのタスクを取得する
	 * @return タスクのリスト
	 */
	@Override
	public List<Task> getAllTasks() {
		// JPAのfindAll()メソッドを使用して全タスクを取得
		return taskRepository.findAll();
	}

	/**
	 * 新しいタスクを保存する
	 * @param task 保存するタスクオブジェクト
	 */
	@Override
	public void saveTask(Task task) {
		// JPAのsave()メソッドを使用してタスクをデータベースに保存
		taskRepository.save(task);
	}

	/**
	 * 指定されたIDのタスクを削除する
	 * @param id 削除するタスクのID
	 */
	@Override
	public void deleteTask(Long id) {
		// JPAのdeleteById()メソッドを使用してタスクを削除
		taskRepository.deleteById(id);
	}

	@Override
	public void toggleCompleted(Long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));

		task.setCompleted(!task.isCompleted());
		taskRepository.save(task);
	}

	@Override
	public void resetCompletedDaily() {
		taskRepository.resetAllCompletedToFalse();
	}

	@Override
	public List<Task> searchTasks(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return taskRepository.findAll();
		}
		return taskRepository.findByTitleContainingIgnoreCase(keyword.trim());
	}

	@Override
	public List<Task> getTodayIncompleteTasks() {
		return taskRepository.findByDueDateAndCompletedFalse(LocalDate.now());
	}

}
