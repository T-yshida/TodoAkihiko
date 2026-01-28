package com.example.demo.repositry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Task;

import jakarta.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Task t SET t.completed = false WHERE t.completed = true")
	int resetAllCompletedToFalse();

	// タイトルの部分一致（大文字小文字無視）
	List<Task> findByTitleContainingIgnoreCase(String keyword);

	List<Task> findByDueDateAndCompletedFalse(LocalDate dueDate);
}
