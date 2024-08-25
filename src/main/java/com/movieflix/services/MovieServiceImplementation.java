package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.Movie;
import com.movieflix.repositories.MovieRepo;

@Service
public class MovieServiceImplementation implements MovieService{
	
	@Autowired
	MovieRepo movrepo;

	@Override
	public String addMovie(Movie mov) {
		
		movrepo.save(mov);
		
		return "movie id added";
	}

	@Override
	public List<Movie> viewMovie() {
		List<Movie> movieList=movrepo.findAll();
		
		return movieList;
	}
	
	

}
