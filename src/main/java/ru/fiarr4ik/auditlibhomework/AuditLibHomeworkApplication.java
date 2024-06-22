package ru.fiarr4ik.auditlibhomework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

    @SpringBootApplication
    @ComponentScan(basePackages = {"ru.fiarr4ik.springbootstarterauditlib", "ru.fiarr4ik.auditlibhomework"})
    public class AuditLibHomeworkApplication {

        public static void main(String[] args) {
             SpringApplication.run(AuditLibHomeworkApplication.class, args);
        }

        @Bean
        public CommandLineRunner run(ExampleService exampleService) {
            return args -> {
                exampleService.serve();
            };
        }
    }
