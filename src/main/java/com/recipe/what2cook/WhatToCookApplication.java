package com.recipe.what2cook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class WhatToCookApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatToCookApplication.class, args);
	}

}
