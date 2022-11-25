package com.app.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBlogAppRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	};

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(SpringbootBlogAppRestApiApplication.class, args);
	}

}
