package com.example.parallelTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.parallelTest.request.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CreateUserTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext) // (4)
//				.alwaysDo(log()).build();
//	}

	@Test
	void 登録なしの場合() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext) // (4)
				.alwaysDo(log()).build();

		ObjectMapper mapper = new ObjectMapper();

		CreateUserRequest request = new CreateUserRequest();
		request.setName("John");
		request.setPassword("password");

		ResultActions results = mockMvc.perform(post("/user").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

}
