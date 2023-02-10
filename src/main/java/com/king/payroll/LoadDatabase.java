package com.king.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner runner(EmployeeRepository employeeRepository) {
        return args -> {
            logger.info("Preloading => " + employeeRepository.save(new Employee("King", "USER")));
            logger.info("Preloading => " + employeeRepository.save(new Employee("David", "ADMIN")));
        };
    }
}
