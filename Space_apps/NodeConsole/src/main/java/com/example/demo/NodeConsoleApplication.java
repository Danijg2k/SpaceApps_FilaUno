package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class NodeConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeConsoleApplication.class, args);	
		SpringApplication.run(SpringBootConsoleApplication.class, args);
	}

}
