package com.cashonline.app.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cashonline.app.CashOnlineApplication;
import com.cashonline.app.models.entity.User;
import com.cashonline.app.models.repository.IUserRepository;
import com.cashonline.app.service.IUserService;
import com.cashonline.app.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ CashOnlineApplication.class})
 
public class UserControllerTest {
	

	private MockMvc mockMvc;
	
	 @Autowired
	 private WebApplicationContext webApplicationContext;
	 
	 @MockBean
	 private IUserRepository UserRepositoryMock;

	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldGetUserWhenValidRequest() throws Exception {
		User user = new User("SiriJesus754@gmail.com","Jesus", "Siri");
		
		when(UserRepositoryMock.findById(1L)).thenReturn(Optional.of(user));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value(user.getEmail()))
				.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
				.andExpect(jsonPath("$.lastName").value(user.getLastName()));
	}
	
	@Test
	public void shouldReturn404WhenUserNotFound() throws Exception {
		
		when(UserRepositoryMock.findById(2L)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	
	
	
	
}
