package com.example.demo.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
