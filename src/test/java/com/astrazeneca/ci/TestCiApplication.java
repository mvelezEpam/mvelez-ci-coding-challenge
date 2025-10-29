package com.astrazeneca.ci;

import org.springframework.boot.SpringApplication;

public class TestCiApplication {

	public static void main(String[] args) {
		SpringApplication.from(CiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
