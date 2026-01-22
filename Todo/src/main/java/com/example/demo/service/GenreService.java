package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Genre;

public interface GenreService {
	List<Genre> getAllGenre();

	void saveGenre(Genre genre);

	void deleteGenre(Genre genre);
}
