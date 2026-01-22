package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Genre;
import com.example.demo.service.GenreService;

@Controller
public class GenreController {
	@Autowired
	private GenreService genreService;

	@PostMapping("/addGenre")
	public String addGenre(@RequestParam("Genre") Genre genre, Model model) {
		genreService.saveGenre(genre);

		return "redirect:/";
	}

	//TODO ジャンルのgetはTaskController側でやろう
	//TODO ジャンルを消したときはmainの所に行くようにする
}
