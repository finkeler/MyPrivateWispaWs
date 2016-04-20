package com.wispa.webserver.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.wispa.webserver.model.User;

/*
 * Used to encapsulate links
 */
@Component
final class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource<User>(user);
        resource.add(linkTo(DomainController.class).slash(user.getId()).withSelfRel());
        return resource;
    }

}