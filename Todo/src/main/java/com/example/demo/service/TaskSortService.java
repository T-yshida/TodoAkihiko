package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Task;

public interface TaskSortService {

	/**
	 * 並び替え条件に応じてタスク一覧を取得する
	 *
	 * @param type 並び替え種別
	 *             createdAt / priority / completed
	 * @return 並び替え済みタスク一覧
	 */
	List<Task> getSortedTasks(String type);
}
