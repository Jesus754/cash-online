package com.cashonline.app.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cashonline.app.models.entity.Loan;

@Repository
public interface ILoanRepository  extends JpaRepository<Loan, Long> {

	Page<Loan> findByUserId( Pageable pageable, Long userId);
}
