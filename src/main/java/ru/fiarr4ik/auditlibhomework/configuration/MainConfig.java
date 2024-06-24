package ru.fiarr4ik.auditlibhomework.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "ru.fiarr4ik.auditlibhomework")
@EnableWebMvc
public class MainConfig {
}
