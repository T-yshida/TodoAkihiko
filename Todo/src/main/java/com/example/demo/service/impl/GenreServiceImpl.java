package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Genre;
import com.example.demo.repositry.GenreRepository;
import com.example.demo.service.GenreService;

public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public List<Genre> getAllGenre() {
		// TODO 自動生成されたメソッド・スタブ
		return genreRepository.findAll();
	}

	@Override
	public void saveGenre(Genre genre) {
		// TODO 自動生成されたメソッド・スタブ
		genreRepository.save(genre);
	}

	@Override
	public void deleteGenre(Genre genre) {
		// TODO 自動生成されたメソッド・スタブ
		genreRepository.delete(genre);
	}

}