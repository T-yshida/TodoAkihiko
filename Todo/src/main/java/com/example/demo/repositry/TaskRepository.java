package com.example.demo.repositry;

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
}
