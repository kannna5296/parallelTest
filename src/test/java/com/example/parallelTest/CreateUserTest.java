package com.example.parallelTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class CreateUserTest extends TestSupport{

	@Test
	@AutoConfigureMockMvc
	@DatabaseSetup(
			value = {"classpath://なんちゃら"}
	)
	void 登録なしの場合() throws Exception {

		Map<String, Object> req = new HashMap<String, Object>();
		req.put("name", "John");
		req.put("password", "pass123");

		ResultActions results = mockMvc.perform(post("/user").content("{\"name\":\"John\",\"password\":\"pass123\"}")).andExpect(status().isOk());
		String aa = "SSS";
	}

}
