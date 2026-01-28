package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Taskエンティティクラス
 * このクラスはデータベースの「Task」テーブルにマッピングされます。
 */
@Entity
@Table(name = "Task") // このクラスが「Task」テーブルに対応していることを指定
@Getter // Lombokを使用してgetterメソッドを自動生成
@Setter // Lombokを使用してsetterメソッドを自動生成
public class Task {

	// タスクIDを主キーとして定義。自動的にインクリメントされる。
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDを自動生成するための戦略
	@Column(name = "id") // データベースの「id」カラムにマッピング
	private Long id;

	// タスクのタイトル。データベースの「title」カラムにマッピング
	@Column(name = "title")
	private String title;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	private Priority priority = Priority.MEDIUM; // デフォルト中

	@Column(name = "completed", nullable = false)
	private boolean completed = false;

	private LocalDateTime createdAt;
}
