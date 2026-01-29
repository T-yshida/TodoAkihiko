package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.Genre;

public interface GenreService {
	List<Genre> getAllGenre();

	void saveGenre(GenreDto genre);

	void deleteGenre(Genre genre);
}