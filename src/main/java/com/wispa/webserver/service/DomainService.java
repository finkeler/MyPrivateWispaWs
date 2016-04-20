package com.wispa.webserver.service;

import java.util.Map;

import com.wispa.webserver.model.User;
import com.wispa.webserver.model.UserNotExistException;

public interface DomainService {
	
    User createUser();
  		
	User findById(long id) throws UserNotExistException;
	
	User findByName(String name) throws UserNotExistException;
	
	//void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id) throws UserNotExistException;

	Map<Long, User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user) throws UserNotExistException;
	
}


