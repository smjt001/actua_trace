package com.actuatrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActuaTraceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuaTraceApplication.class, args);
        System.out.println("Started..>");
	}

}
