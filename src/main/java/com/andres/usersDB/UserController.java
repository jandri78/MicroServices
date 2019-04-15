package com.andres.usersDB;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;
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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "/users")
	public List<User> AllUsers(){
		
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public Resource<User> getOne(@PathVariable int id) throws userNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new userNotFoundException("id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).AllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//return user;
		
		return resource;
	}   
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> addOne(@Valid @RequestBody User user) {
	
		User saveUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteOne(@PathVariable int id) throws userNotFoundException {
	   userRepository.deleteById(id);
		

	}
	
	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> retrieveAllUser(@PathVariable int id) throws userNotFoundException{
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent()) {
			throw new userNotFoundException("id"+id);
		}
		
		
		return userOptional.get().getPost();
	}
	
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) throws userNotFoundException {
	
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent()) {
			throw new userNotFoundException("id"+id);
		}
		User user = userOptional.get();
		
		post.setUser(user);
		postRepository.save(post);
				
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	
}
