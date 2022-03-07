package com.example.parallelTest;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
public class GenerateOasFile {

  private static final String DOCS_URL = "/v2/api-docs";

  private static final String DOCS_LOCATION = "docs/openapi.json";

  @Autowired
  protected WebApplicationContext context;

  @Test
  public void generateOasFile() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    mockMvc.perform(get(DOCS_URL).contentType(MediaType.APPLICATION_JSON))
            .andDo(result -> FileUtils.writeStringToFile(
                    new File(DOCS_LOCATION),
                    new String(result.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8)));
  }
}