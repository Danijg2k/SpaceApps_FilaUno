package com.example.demo;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApplication 
  implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws IOException {

		// TODO Auto-generated method stub
		//File log = funciones.crearRaiz();
		Scanner s = new Scanner(System.in);
		// Si la raíz del nodo no está creada la crea. En caso contrario accede.
		funciones.createRoot();
		// Mostramos el menú
		funciones.menu();
    }
}
