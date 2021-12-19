package com.example.parallelTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static com.example.parallelTest.TestSupport.mockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class CreateUserTest {

	@Test
	void 登録なしの場合() throws Exception {

		Map<String, Object> req = new HashMap<String, Object>();
		req.put("name", "John");
		req.put("password", "pass123");

		ResultActions results = mockMvc.perform(post("/user").content("{\"name\":\"John\",\"password\":\"pass123\"}")).andExpect(status().isOk());
		String aa = "SSS";
	}

}
