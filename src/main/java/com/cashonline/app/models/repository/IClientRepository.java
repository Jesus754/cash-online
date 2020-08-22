package com.cashonline.app.models.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashonline.app.models.entity.Client;

@Repository
public interface IClientRepository extends CrudRepository<Client, Long> {
	
	public Optional<Client> findById(Long id);
	
}
