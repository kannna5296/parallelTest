package com.example.parallelTest;

import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

@Component
public class TestSupport extends TestSupportSupport{

    public static WebApplicationContext wac;
    public static MockMvc mockMvc;
    public static JdbcTemplate jdbc;
    public static DataSource source;
    protected static Destination destination;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        destination = new DataSourceDestination(source);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
         //       .apply(SecurityMockMvcConfigurers.springSecurity())
//                .addFilters(new BeforeMockMvcFilter())
//                .addFilters(new AfterMockMvcFilter()).build();
    }

    @Autowired
    public void setWac(WebApplicationContext wac) {
        this.wac = wac;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Autowired
    public void setSource(DataSource source) {
        this.source = source;
    }

}
