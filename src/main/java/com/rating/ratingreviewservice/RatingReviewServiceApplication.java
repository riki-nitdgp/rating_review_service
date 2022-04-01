package com.rating.ratingreviewservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.rating.ratingreviewservice")
public class RatingReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingReviewServiceApplication.class, args);
	}

}
