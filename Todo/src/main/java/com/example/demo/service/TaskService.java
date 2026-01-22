package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Task;

/**
 * タスク操作のビジネスロジックを定義するインターフェース
 * 実際の実装は TaskServiceImpl クラスに委任します。
 */
public interface TaskService {

	/**
	 * すべてのタスクを取得するメソッド
	 * @return タスクのリスト
	 */
	List<Task> getAllTasks();

	/**
	 * 新しいタスクを保存するメソッド
	 * @param task 保存するタスクオブジェクト
	 */
	void saveTask(Task task);

	/**
	 * 指定されたIDのタスクを削除するメソッド
	 * @param id 削除するタスクのID
	 */
	void deleteTask(Long id);

	void toggleCompleted(Long id);
}
