package com.cashonline.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cashonline.app.models.entity.Loan;

public interface ILoanService {
	
	public Page<Loan> findAll(PageRequest pageRequest, Long userId); 
}
