package com.cashonline.app.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.cashonline.app.models.entity.Loan;
import com.cashonline.app.models.repository.ILoanRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class LoanControllerTest {
	
	private MockMvc mockMvc;
	
	@MockBean
	private ILoanRepository LoanRepositoryMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldGetPagingWhenValidRequest_Get_All_Method() throws Exception {
		PageRequest pageRequest = PageRequest.of(2, 1);
		
		List<Loan> loans = new ArrayList<>();
		loans.add(new Loan());
		loans.add(new Loan());
		
		Page<Loan> pagedResponse = new PageImpl<Loan>(loans,pageRequest,22);

		when(LoanRepositoryMock.findByUserId(pageRequest,1L)).thenReturn(pagedResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/loans/").param("page", "3").param("size", "1").param("user_id","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pagins.total").value(pagedResponse.getTotalElements()))
				.andExpect(jsonPath("$.pagins.size").value(pagedResponse.getSize()))
				.andExpect(jsonPath("$.pagins.page").value(pagedResponse.getNumber() + 1));
	}
	
	@Test
	public void shouldGetPagingWhenValidRequestWithoutUser_Get_All_Method() throws Exception {
		PageRequest pageRequest = PageRequest.of(2, 1);
		
		List<Loan> loans = new ArrayList<>();
		loans.add(new Loan());
		loans.add(new Loan());
		
		Page<Loan> pagedResponse = new PageImpl<Loan>(loans,pageRequest,22);

		when(LoanRepositoryMock.findAll(pageRequest)).thenReturn(pagedResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/loans/").param("page", "3").param("size", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pagins.total").value(pagedResponse.getTotalElements()))
				.andExpect(jsonPath("$.pagins.size").value(pagedResponse.getSize()))
				.andExpect(jsonPath("$.pagins.page").value(pagedResponse.getNumber() + 1));
	}
	
	@Test
	public void shouldReturn404WhenNotValidRequest_Get_User_Method() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/loans/").param("page", "0").param("size", "0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/loans/").param("page", "0").param("size", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/loans/").param("page", "1").param("size", "0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	
	
}
