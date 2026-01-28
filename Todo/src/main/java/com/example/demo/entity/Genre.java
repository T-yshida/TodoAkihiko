package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Genres") // このクラスが「Task」テーブルに対応していることを指定
@Getter // Lombokを使用してgetterメソッドを自動生成
@Setter // Lombokを使用してsetterメソッドを自動生成
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDを自動生成するための戦略
	@Column(name = "id") // データベースの「id」カラムにマッピング
	private Integer id;

	// タスクのタイトル。データベースの「title」カラムにマッピング
	@Column(name = "genre")
	private String genre;
}
