package com.example.parallelTest;

import com.example.parallelTest.util.XlsDataSetLoader;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@SpringBootTest
@DbUnitConfiguration(
        dataSetLoader = XlsDataSetLoader.class
)
public class TestSupportSupport {

    protected static ObjectMapper mapper;

    public void setUp() {
        mapper = Jackson2ObjectMapperBuilder.json()
                .propertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy()).build();
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION,false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
