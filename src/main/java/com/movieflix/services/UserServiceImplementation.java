package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.User;
import com.movieflix.repositories.UserRepo;

@Service
public class UserServiceImplementation implements UserService{
	
	 @Autowired
	UserRepo urepo;

	@Override
	public String addUsers(User usr) {
		urepo.save(usr);
		
		return "user is created";
	}

	@Override
	public boolean emailExist(String email) {
//		Checking whether the user exist with entered email
		if(urepo.findByEmail(email)==null) {
//			if user doesn't exist, return false
			return false;
		}else {
//			otherwise return true
			return true;
		}
	}

	@Override
	public boolean checkUser(String email, String password) {
//		Checking email and getting user details from DB
		User usr=urepo.findByEmail(email);
//		Getting DB password of user using email
		String db_password=usr.getPassword();
//		checking whether user entered password and DB password
		if(db_password.equals(password)) {
//			if same, returning true
			return true;
		}else {
//			if not same, return false
			return false;
		}
		
	}

	@Override
	public List<User> ViewUser() {
		List<User> userList=urepo.findAll();
		return userList;
	}

	@Override
	public User getUser(String email) {
//		Inside the getUser getting the details from the DB and storing in the user format
		User user=urepo.findByEmail(email);
		return user;
	}

	@Override
	public void updateUser(User user) {
		urepo.save(user);
		
	}

	@Override
	public void deleteUser(int id) {
		urepo.deleteById(id);
		
	}

}
	 


