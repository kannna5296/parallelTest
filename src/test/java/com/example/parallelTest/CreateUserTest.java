package com.example.parallelTest;

import static org.assertj.db.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.parallelTest.request.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.db.type.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.db.type.Table;
import org.assertj.db.type.Changes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreateUserTest {

	private MockMvc mockMvc;

	private static Source dataSource = new Source("jdbc:sqlserver://localhost:1433;databaseName=test;loginTimeout=30;socketTimeout=30000","sa", "passWord567");

	private WebApplicationContext webApplicationContext;

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public void setWac (WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext) // (4)
				.alwaysDo(log()).build();
	}

	@Test
	void 登録なしの場合() throws Exception {

		CreateUserRequest request = new CreateUserRequest();
		request.setName("John");
		request.setPassword("password");

		Table table = new Table(dataSource,"app_user");
		Changes changes = new Changes(table).setStartPointNow();

		ResultActions results = mockMvc.perform(post("/user").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

		changes.setEndPointNow();

		assertThat(changes)
				.ofCreation()
				.change()
				.rowAtEndPoint()
				.value("name")
				.isEqualTo("John")
				.value("password")
				.isEqualTo("password");
	}

}
