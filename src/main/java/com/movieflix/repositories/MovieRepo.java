package com.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.entities.Movie;


public interface MovieRepo  extends JpaRepository<Movie, Integer>{

	
}
