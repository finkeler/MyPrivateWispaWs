package com.wispa.webserver.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wispa.webserver.model.User;
import com.wispa.webserver.service.DomainService;



@RestController
public class DomainController {

	@Autowired
	DomainService domainService;  //Service which will do all data retrieval/manipulation work
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Map<Long, User>> listAllUsers() {
		Map<Long, User> users = domainService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<Map<Long, User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<Long, User>>(users, HttpStatus.OK);
	}
	
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    ResponseEntity<Void> createUser() {
        User user = domainService.createUser();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(DomainController.class).slash(user.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
    	
    	User user = domainService.findById(userId);
    	return new ResponseEntity<User>(user, HttpStatus.OK) ;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    ResponseEntity<Void> removeUser(@PathVariable Long userId) {
    	domainService.deleteUserById(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
<<<<<<< HEAD
=======

	
	
>>>>>>> branch 'master' of https://github.com/finkeler/MyPrivateWispaWs.git
}
