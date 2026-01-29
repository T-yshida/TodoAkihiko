package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.Genre;
import com.example.demo.repositry.GenreRepository;
import com.example.demo.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public List<Genre> getAllGenre() {
		// TODO 自動生成されたメソッド・スタブ
		return genreRepository.findAll();
	}

	@Override
	public void saveGenre(GenreDto genre) {
		// TODO 自動生成されたメソッド・スタブ
		Genre saveGenre = new Genre();

		saveGenre.setGenre(genre.getGenre());

		genreRepository.save(saveGenre);
	}

	@Override
	public void deleteGenre(Genre genre) {
		// TODO 自動生成されたメソッド・スタブ
		genreRepository.delete(genre);
	}

}