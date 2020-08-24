package com.cashonline.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cashonline.app.config.exception.BadRequestException;
import com.cashonline.app.config.exception.InternalServerErrorException;
import com.cashonline.app.models.entity.Loan;
import com.cashonline.app.models.repository.ILoanRepository;

@Service
public class LoanServiceImpl implements ILoanService{

	@Autowired
	private ILoanRepository ILoanRepository;

	

	public Page<Loan> findAll(Integer page, Integer size, Long userId) { 
		if (page < 1)
			throw new BadRequestException("El parametro page no puede ser menor a 1");
		try {
			PageRequest pageRequest = PageRequest.of(page - 1, size);
			if (userId != null )
				return ILoanRepository.findByUserId(userId, pageRequest);
			return ILoanRepository.findAll(pageRequest);
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
