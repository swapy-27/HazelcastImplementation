package com.swapy.hazelcast_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class HazelcastAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastAppApplication.class, args);
	}

}
