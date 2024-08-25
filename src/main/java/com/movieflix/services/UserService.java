package com.movieflix.services;

import java.util.List;

import com.movieflix.entities.User;

public interface UserService {
	public String addUsers(User user);
	
	public boolean emailExist(String email);
	
	public boolean checkUser(String email, String password);
	
	public List<User> ViewUser();
	
	public User getUser(String email);
	
	public void updateUser(User user);

	public void deleteUser(int id);

	

	
	
	

}
