package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.TaskService;

@Component
public class DailyResetScheduler {

	private final TaskService taskService;

	public DailyResetScheduler(TaskService taskService) {
		this.taskService = taskService;
	}

	// 毎日 00:00 に実行（日本時間）
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Tokyo")
	public void resetCompletedEveryMidnight() {
		taskService.resetCompletedDaily();
	}
}
