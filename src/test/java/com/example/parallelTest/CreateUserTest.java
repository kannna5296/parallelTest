package com.example.parallelTest;

import static com.github.springtestdbunit.annotation.DatabaseOperation.CLEAN_INSERT;
import static org.assertj.db.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.example.parallelTest.request.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
        databaseOperationLookup = CustomDatabaseOperationLookup.class
)
@TestExecutionListeners({
        DbUnitTestExecutionListener.class
})
class CreateUserTest {


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
  void 名前が同一のデータが存在しない場合() throws Exception {

    CreateUserRequest request = new CreateUserRequest();
    request.setName("John");
    request.setPassword("password");

    Table table = new Table(dataSource, "app_user");
    Changes changes = new Changes(table).setStartPointNow();

    ResultActions results =
        mockMvc
            .perform(
                post("/user")
                    .content(mapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(log());

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

  @Test
  @DatabaseSetup(
          value = {
                  "/data/ユーザ作成/app_user.xlsx"
          },
          type = CLEAN_INSERT
  )
  void 名前が同一のデータが存在する場合() throws Exception {

    CreateUserRequest request = new CreateUserRequest();
    request.setName("John");
    request.setPassword("password");

    Table table = new Table(dataSource, "app_user");
    Changes changes = new Changes(table).setStartPointNow();

    ResultActions results =
            mockMvc
                    .perform(
                            post("/user")
                                    .content(mapper.writeValueAsString(request))
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andDo(log());

    changes.setEndPointNow();

    //DBに変更がないこと
    assertThat(changes).ofCreation().hasNumberOfChanges(0);
    assertThat(changes).ofModification().hasNumberOfChanges(0);
    assertThat(changes).ofDeletion().hasNumberOfChanges(0);


  }
}
