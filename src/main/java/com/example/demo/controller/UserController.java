package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.userRepo;


@RestController
@RequestMapping("user/api")
public class UserController {
	@Autowired
	private userRepo repo;
	
//get all user details	
	@GetMapping("/allUsers")
	public List<User> allUsers(){
		return repo.findAll();
		
	}
//get User by ID
	@GetMapping("/{id}")
	public User getById(@PathVariable Long id) {
		return this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
		
	}
	// create user
		@PostMapping("/create")
		public User createUser(@RequestBody User user) {
			return this.repo.save(user);
		}
		
		// update user
		@PutMapping("/{id}")
		public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
			 User existingUser = this.repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
			 existingUser.setFirstName(user.getFirstName());
			 existingUser.setLastName(user.getLastName());
			 existingUser.setCarBrand(user.getCarBrand());
			 return this.repo.save(existingUser);
		}
		
		// delete user by id
		@DeleteMapping("/{id}")
		public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
			 User existingUser = this.repo.findById(userId)
						.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
			 this.repo.delete(existingUser);
			 return ResponseEntity.ok().build();
		}
	}


