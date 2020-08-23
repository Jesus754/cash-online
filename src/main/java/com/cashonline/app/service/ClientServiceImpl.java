package com.cashonline.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cashonline.app.config.exception.InternalServerErrorException;
import com.cashonline.app.config.exception.NotFoundException;
import com.cashonline.app.models.entity.Client;
import com.cashonline.app.models.repository.IClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientRepository clientDao;

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		try {
			Optional<Client> oClient = clientDao.findById(id);
			if(!oClient.isPresent()) {
				throw new NotFoundException("El cliente con id " + id + " no existe");
			}
			return oClient.get();
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public Client save(Client client) {
		try {
			return clientDao.save(client);
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
	try{
		clientDao.deleteById(id);
	}catch (DataAccessException e) {
		throw new InternalServerErrorException(e.getMessage());
	}
	}
	

}
