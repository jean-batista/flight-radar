package com.flightradarmsn.flightradar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightradarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightradarApplication.class, args);
	}

}
