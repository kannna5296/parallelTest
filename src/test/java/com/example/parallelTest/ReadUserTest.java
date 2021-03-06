package com.example.parallelTest;

import static com.github.springtestdbunit.annotation.DatabaseOperation.CLEAN_INSERT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.operation.MicrosoftSqlDatabaseOperationLookup;
import org.assertj.db.type.Source;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@DbUnitConfiguration(
        dataSetLoader = XlsDataSetLoader.class,
        databaseOperationLookup = MicrosoftSqlDatabaseOperationLookup.class
)
@TestExecutionListeners({
        DbUnitTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
class ReadUserTest {

  private MockMvc mockMvc;

  private static Source dataSource =
      new Source(
          "jdbc:sqlserver://localhost:1433;databaseName=test;loginTimeout=30;socketTimeout=30000",
          "sa",
          "passWord567");

  private WebApplicationContext webApplicationContext;

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public void setWac(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext) // (4)
            .alwaysDo(log())
            .build();
  }

  @Test
  @DatabaseSetup(
          value = {
                  "/data/?????????????????????/app_user.xlsx"
          },
          type = CLEAN_INSERT
  )
  void ?????????1???() throws Exception {

    ResultActions results =
            mockMvc
                    .perform(
                            get("/user"))
                    .andExpect(status().isOk())
                    .andDo(log());

    String responseString = results.andReturn().getResponse().getContentAsString();
    JSONObject result = new JSONObject(responseString);
    JSONArray array = result.getJSONArray("users");
    JSONObject result0 = array.getJSONObject(0);

    assertThat(result0.get("id"), equalTo(101));
    assertThat(result0.get("name"), equalTo("John"));
    assertThat(result0.get("password"), equalTo("password"));

    //TODO ????????????????????????ASSERT??????
  }
}
