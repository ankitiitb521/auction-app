package com.ankit.bidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ankit.bidding")
public class BiddingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiddingApplication.class, args);
	}

}
