package com.example.EsimBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsimBackApplication implements CommandLineRunner {
	@Autowired
	private ConnectionService connectionService;

	public static void main(String[] args) {
		SpringApplication.run(EsimBackApplication.class, args);
	}
	@Override
	public void run(String... args) {
		connectionService.checkBmwBackHealth();
	}
}
