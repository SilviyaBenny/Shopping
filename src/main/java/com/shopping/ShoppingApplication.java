package com.shopping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingApplication {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingApplication.class);
	public static void main(String[] args) {
		  logger.info("this is a info message");
	      logger.warn("this is a warn message");
	      logger.error("this is a error message");
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
