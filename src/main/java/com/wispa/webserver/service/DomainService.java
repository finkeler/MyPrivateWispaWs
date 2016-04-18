package com.wispa.webserver.service;

import java.util.Map;

import com.wispa.webserver.model.User;

public interface DomainService {
	
    User createUser();
  		
	User findById(long id);
	
	User findByName(String name);
	
	//void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	Map<Long, User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
}


