package com.cashonline.app.service;

import org.springframework.data.domain.Page;


import com.cashonline.app.models.entity.Loan;

public interface ILoanService {
	
	public Page<Loan> findAll(Integer page, Integer size, Long user_id); 
}
