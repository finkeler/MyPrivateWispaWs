package com.wispa.webserver.service;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.wispa.webserver.model.User;

//@Transactional
@Service("domainService")
public class DomainServiceImpl implements DomainService{
	
	private final AtomicLong counter = new AtomicLong();
	
    private final Map<Long, User> users = new HashMap<Long, User>();

    private final Object monitor = new Object();

    @Override
    public User createUser() {
        synchronized (this.monitor) {
            long id = this.counter.getAndIncrement();
            User user = new User(id);

            this.users.put(id, user);

            return user;
        }
    }	

	public Map<Long, User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		
		return users.get(id);
	}
	
	public User findByName(String name) {
		
		for(User user : users.values()){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		
		return null;
	}
	
	public void updateUser(User user) {
		users.put(user.getId(), user);
	}

	public void deleteUserById(long id) {
		users.remove(id);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}


	public void deleteAllUsers() {
		users.clear();
	}
	
}


