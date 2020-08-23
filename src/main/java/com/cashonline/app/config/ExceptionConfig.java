package com.cashonline.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.app.config.exception.BadRequestException;
import com.cashonline.app.config.exception.InternalServerErrorException;
import com.cashonline.app.config.exception.NotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundException(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(createResponse(e));
	}
	
	
	@ExceptionHandler({BadRequestException.class})
	public ResponseEntity<?> badRequestException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON)
				.body(createResponse(e));
	}
	
	@ExceptionHandler({InternalServerErrorException.class})
	public ResponseEntity<?> InternalServerErrorException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.contentType(MediaType.APPLICATION_JSON)
				.body(createResponse(e));
	}
	
	
	private Map<String,Object> createResponse(Exception e) {
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Ocurrio un error");
		response.put("error", e.getMessage()); 
		return response;
	}
	
		
}
