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
public class UserController {

	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> AllUsers(){
		
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public Resource<User> getOne(@PathVariable int id) throws userNotFoundException {
		User user = service.getOne(id);
		if (user == null) {
			throw new userNotFoundException("id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).AllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//return user;
		
		return resource;
	}   
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> addOne(@Valid @RequestBody User user) {
	
		User saveUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public User deleteOne(@PathVariable int id) throws userNotFoundException {
		User user = service.deleteOne(id);
		
		if (user == null) {
			throw new userNotFoundException("id-"+id);
		}
		
		return user;
	}
	
}
