package com.cashonline.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.app.models.entity.User;
import com.cashonline.app.service.IUserService;


@RestController
public class UserController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);
	
	@Autowired 
	private IUserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<?> CreateUser(@RequestBody User user) {
		
		User userResponse = userService.save(user);
		
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Usuario creado con exito");
		response.put("user", userResponse);
		
		LOGGER.info("POST /users: CORRECTO - " + user.getFirstName()+ " " + user.getLastName() + " - " + user.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		
		User user = userService.findById(id);
		
		LOGGER.info("GET /users/"+id.toString()+": CORRECTO - " + user.getFirstName()+ " " + user.getLastName() + " - " + user.getEmail());
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(user);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		
		userService.delete(id);
		
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Usuario eliminado con exito");
		
		LOGGER.info("DELETE /users/"+ id.toString() +": CORRECTO" );
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
}
