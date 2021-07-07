package com.spring.testable.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@Configuration
//@ComponentScan(basePackages = {"com.spring.testable.mock.dao"})
//@MapperScan({"com.****.***.mapper"})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
//@ContextConfiguration(classes = TestConfiguration.class)
public class TestConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(TestConfiguration.class);
    }
}
