package com.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userv;
	
	@Autowired
	MovieService movserv;

	@PostMapping("register")
	public String addUser(@ModelAttribute User usr) {
		
		boolean status=userv.emailExist(usr.getEmail());
		if(status==true) 
		{
			return "registerfail";
		}else {
			userv.addUsers(usr);
			return "registersuccess";
		}
		
	}
	
	@PostMapping("login")
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		boolean loginStatus=userv.checkUser(email, password);
			if(loginStatus==true)
			{
				session.setAttribute("email", email);
				
				if(email.equals("admin@gmail.com")) 
				{
					return "adminhome";
				}
				else 
				{
					return "home";
				}
			}
			else
			{
				return "loginfail";
			}
		}
	
	@GetMapping("viewuser")
	public String viewUser(Model model) {
		List<User> userList=userv.ViewUser();
		model.addAttribute("user", userList);
		return "viewuser";
	}
	
	@GetMapping("exploremovies")
	public String exploreMovie(Model model, HttpSession session) 
	{
//		Taking the email from the user (OR) Getting the email from session
		String email=(String)session.getAttribute("email");
		
//		Getting the user details from user
		User usr=userv.getUser(email);
		
//		Checking the user is premium or not
		if(usr.isPremium()==true)
		{
//			Getting the list of movies
			List<Movie> movieList=movserv.viewMovie();
			
//			Adding the movie details in model
			model.addAttribute("movie", movieList);
			
//			if user is Premium, show movies
			return "viewmovieusere";
		} 
		else
		{
//			 if user is non-Premium, make payment
			return "payment";
		}
		
	}

	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
	
	@GetMapping("updateuserform")
	public String showUpdateUserForm() {
		return "updateuserform";
	}

	@GetMapping("deleteuserform")
	public String showDeleteUserForm() {
		return "deleteuserform";
	}
	
	@PostMapping("updateuser")
	public String updateUser(@ModelAttribute User usr) {
		userv.updateUser(usr);
		return "updateusersuccess";
	}

	@GetMapping("deleteuser")
	public String deleteUser(@RequestParam int id) {
		userv.deleteUser(id);
		return "deletesuccess";
	}
	

}



