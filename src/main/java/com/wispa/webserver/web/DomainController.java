package com.wispa.webserver.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wispa.webserver.model.User;
import com.wispa.webserver.model.UserNotExistException;
import com.wispa.webserver.service.DomainService;



@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class DomainController {

	private final DomainService domainService;  //Service which will do all data retrieval/manipulation work
		
    private final UserResourceAssembler userResourceAssembler;
                  
    private final DomainResourceAssmebler domainResourceAssembler;


    @Autowired
    DomainController(DomainService domainService, UserResourceAssembler userResourceAssembler, DomainResourceAssmebler domainResourceAssembler) {
        this.domainService = domainService;
        this.userResourceAssembler = userResourceAssembler;
        this.domainResourceAssembler = domainResourceAssembler;
    }
	
    @RequestMapping(method = RequestMethod.POST, value = "")
    ResponseEntity<Void> createUser() {
        User user = domainService.createUser();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(DomainController.class).slash(user.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource<User>> getUser(@PathVariable Long userId) throws UserNotExistException {

		User user = domainService.findById(userId);
		
		Resource<User> resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<Resource<User>>(resource, HttpStatus.OK) ;

    }
    
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource<User>>> listAllUsers() {
		//Map<Long, User> users = domainService.findAllUsers();

		Resources<Resource<User>> resource = domainResourceAssembler.toResource(domainService);
//		if(resource.hasLinks() == false){
//			return new ResponseEntity<Resources<Resource<User>>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//		}		

        return new ResponseEntity<Resources<Resource<User>>>(resource, HttpStatus.OK);
	}
	
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    ResponseEntity<Void> removeUser(@PathVariable Long userId) throws UserNotExistException {
    	domainService.deleteUserById(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
       
    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateUser(@PathVariable Integer userId, @RequestBody Map<String, String> body) throws UserNotExistException, MissingKeyException {
    	
    	final String NAME_KEY = "name";

    	String userName = "default";
        if (body.containsKey(NAME_KEY)) {
            userName = body.get(NAME_KEY);
            
        	User user = domainService.findById(userId);
        	user.setName(userName);
        	
        	domainService.updateUser(user);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new MissingKeyException(NAME_KEY);
    }
    
    @ExceptionHandler(UserNotExistException.class)
    ResponseEntity<String> handleNotFounds(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MissingKeyException.class)
    ResponseEntity<String> handleBadRequest(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
}
