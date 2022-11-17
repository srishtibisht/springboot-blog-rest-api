package com.app.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootBlogAppRestApiApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(SpringbootBlogAppRestApiApplication.class, args);
	}

}
