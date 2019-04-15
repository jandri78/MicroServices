package com.andres.users;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController2 {

	@Autowired
	private UserDaoServiceM serviceM;
	
	@GetMapping(path = "/usersM")
	public List<UserM> AllUsers(){
		
		return serviceM.findAll();
	}
	
	@GetMapping(path = "/usersM/{id}")
	public Resource<UserM> getOne(@PathVariable int id) throws userNotFoundException {
		UserM user = serviceM.getOne(id);
		if (user == null) {
			throw new userNotFoundException("id-"+id);
		}
		
		Resource<UserM> resource = new Resource<UserM>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).AllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//return user;
		
		return resource;
	}   
	
	@PostMapping(path = "/usersM")
	public ResponseEntity<Object> addOne(@Valid @RequestBody UserM user) {
	
		UserM saveUser = serviceM.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/usersM/{id}")
	public UserM deleteOne(@PathVariable int id) throws userNotFoundException {
		UserM user = serviceM.deleteOne(id);
		
		if (user == null) {
			throw new userNotFoundException("id-"+id);
		}
		
		return user;
	}
	
	
	
}
