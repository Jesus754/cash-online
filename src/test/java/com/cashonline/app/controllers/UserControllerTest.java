package com.cashonline.app.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.cashonline.app.models.entity.User;
import com.cashonline.app.models.repository.IUserRepository;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest()
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
	public void shouldGetUserWhenValidRequest_Get_User_Method() throws Exception {
		User user = new User("SiriJesus754@gmail.com", "Jesus", "Siri");

		when(UserRepositoryMock.findById(1L)).thenReturn(Optional.of(user));

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value(user.getEmail()))
				.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
				.andExpect(jsonPath("$.lastName").value(user.getLastName()));
	}

	@Test
	public void shouldReturn404WhenUserNotFound_Get_User_Method() throws Exception {

		when(UserRepositoryMock.findById(2L)).thenReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 2).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test 
	public void shouldCreateUserWhenValidRequest_Create_User_Method() throws Exception {
		Gson gson = new Gson();
		
		User user = new User("SiriJesus754@gmail.com", "Jesus", "Siri");
		
		when(UserRepositoryMock.save(user)).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(user))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());	
	}
	
	@Test 
	public void ShouldDeleteUserWhenValidRequest_Delete_User_Method() throws Exception {
		User user = new User("SiriJesus754@gmail.com", "Jesus", "Siri");
		
		when(UserRepositoryMock.findById(3L)).thenReturn(Optional.of(user));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}",3).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());	
	}
	
	@Test
	public void shouldReturn404WhenUserNotFound_Delete_Method() throws Exception {
		
		when(UserRepositoryMock.findById(3L)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}",3).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());	
	}
	
}
