package com.cashonline.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cashonline.app.config.exception.InternalServerErrorException;
import com.cashonline.app.models.entity.Loan;
import com.cashonline.app.models.repository.ILoanRepository;

@Service
public class LoanServiceImpl implements ILoanService{

	@Autowired
	private ILoanRepository ILoanRepository;

	

	public Page<Loan> findAll(PageRequest pageRequest, Long userId) {
		try {
			if (userId != null ) {
				return ILoanRepository.findByUserId(pageRequest, userId);
			}
			return ILoanRepository.findAll(pageRequest);
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
