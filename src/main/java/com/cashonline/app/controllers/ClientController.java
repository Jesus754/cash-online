package com.cashonline.app.controllers;

import java.util.HashMap;
import java.util.Map;

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

import com.cashonline.app.models.entity.Client;
import com.cashonline.app.service.IClientService;


@RestController
public class ClientController {
	
	@Autowired 
	private IClientService clientService;
	
	@PostMapping("/users")
	public ResponseEntity<?> CreateClient(@RequestBody Client client) {
		Client clientResponse = clientService.save(client);
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Cliente creado con exito");
		response.put("client", clientResponse);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<?> getClient(@PathVariable Long id) {
		Client client = clientService.findById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(client);
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		clientService.delete(id);
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Cliente eliminado con exito");
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
}
