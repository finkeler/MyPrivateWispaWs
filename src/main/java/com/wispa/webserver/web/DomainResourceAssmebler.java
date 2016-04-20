package com.wispa.webserver.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.wispa.webserver.model.User;
import com.wispa.webserver.service.DomainService;


@Component
final class DomainResourceAssmebler implements ResourceAssembler<DomainService, Resources<Resource<User>>> {

    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    DomainResourceAssmebler(UserResourceAssembler userResourceAssembler) {
        this.userResourceAssembler = userResourceAssembler;
    }

    @Override
    public Resources<Resource<User>> toResource(DomainService domain) {
        Set<Resource<User>> users = new HashSet<>();

        for (User user : domain.findAllUsers().values()) {
        	users.add(this.userResourceAssembler.toResource(user));
        }

        Resources<Resource<User>> resource = new Resources<>(users);
        resource.add(linkTo(DomainController.class).withSelfRel());


        return resource;
    }

}