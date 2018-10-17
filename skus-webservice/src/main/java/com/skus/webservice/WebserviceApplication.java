package com.skus.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.skus.*")
public class WebserviceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(WebserviceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }
}
