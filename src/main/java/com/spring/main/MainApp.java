package com.spring.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan({"com.spring.controller","com.spring.main"})
@EntityScan("com.spring.domain")
@EnableJpaRepositories("com.spring.repository")
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);

    }


}
