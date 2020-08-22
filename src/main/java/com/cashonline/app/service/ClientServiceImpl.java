package com.cashonline.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashonline.app.config.exception.NotFoundException;
import com.cashonline.app.models.entity.Client;
import com.cashonline.app.models.repository.IClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientRepository clientDao;

	@Override
	public Client findById(Long id) {
		Optional<Client> oClient = clientDao.findById(id);
		if(!oClient.isPresent()) {
			throw new NotFoundException("El usuario con id " + id + " no existe");
		}
		return oClient.get();
	}

	

}
