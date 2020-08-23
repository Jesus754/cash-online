package com.cashonline.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cashonline.app.models.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
	
	
}
