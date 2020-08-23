package com.cashonline.app.service;




import org.springframework.stereotype.Service;

import com.cashonline.app.models.entity.User;

@Service
public interface IUserService {
	
	public User findById(Long Id);
	
	public User save(User user);
	
	public void delete(Long id);
	
}
