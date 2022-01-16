package com.example.parallelTest;

import static com.github.springtestdbunit.annotation.DatabaseOperation.CLEAN_INSERT;
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

import javax.transaction.Transactional;

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
                  "/data/ユーザ作成/app_user.xlsx"
          },
          type = CLEAN_INSERT
  )
  void データ1件() throws Exception {

    ResultActions results =
            mockMvc
                    .perform(
                            get("/user"))
                    .andExpect(status().isOk())
                    .andDo(log());

    String responseString = results.andReturn().getResponse().getContentAsString();
    //JSONObject result = new JSONObject(responseString);
    String a  ="ss";

    //DBに変更がないこと
    //assertThat(changes).ofCreation().hasNumberOfChanges(0);
    //assertThat(changes).ofModification().hasNumberOfChanges(0);
    //assertThat(changes).ofDeletion().hasNumberOfChanges(0);


  }
}
