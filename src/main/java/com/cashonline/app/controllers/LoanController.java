package com.cashonline.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.app.models.entity.Loan;
import com.cashonline.app.service.ILoanService;

@RestController
public class LoanController {
	
	private static final Logger LOGGER = LogManager.getLogger(LoanController.class);
	
	@Autowired 
	private ILoanService loanService;
	
	@GetMapping("/loans")
	public ResponseEntity<?> getAll(@RequestParam(name = "page") Integer pageValue, 
			@RequestParam(name = "size") Integer sizeValue,
			@RequestParam(name = "user_id", required = false) Long userId) {
		
		Page<Loan> page =  loanService.findAll(pageValue, sizeValue, userId);
		
		Map<String,Object> response = new HashMap<>();
		Map<String, Object> paging =new HashMap<>();
		paging.put("size", sizeValue);
		paging.put("page", pageValue);
		paging.put("total",page.getTotalElements());
		response.put("items", page.getContent());
		response.put("pagins", paging);
		
		StringBuilder logMessage = new StringBuilder("GET /loans?page=" + pageValue.toString() + "&size=" + sizeValue.toString());
		if(userId != null)
			logMessage.append("&user_id=" + userId.toString());
		logMessage.append(" - CORRECTO");
		LOGGER.info(logMessage);
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}






}
