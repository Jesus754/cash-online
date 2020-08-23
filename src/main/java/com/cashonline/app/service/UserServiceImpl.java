package com.cashonline.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cashonline.app.config.exception.InternalServerErrorException;
import com.cashonline.app.config.exception.NotFoundException;
import com.cashonline.app.models.entity.User;
import com.cashonline.app.models.repository.IUserRepository;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		try {
			Optional<User> oUser = userRepository.findById(id);
			if(!oUser.isPresent()) {
				throw new NotFoundException("El usuario con id " + id + " no existe");
			}
			return oUser.get();
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public User save(User user) {
		try {
			return userRepository.save(user);
		}catch (DataAccessException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
	try{
		userRepository.deleteById(id);
	}catch (DataAccessException e) {
		throw new InternalServerErrorException(e.getMessage());
	}
	}
	

}
