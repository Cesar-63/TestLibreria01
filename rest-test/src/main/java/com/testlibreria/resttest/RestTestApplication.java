package com.testlibreria.resttest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTestApplication implements CommandLineRunner{

	@Autowired
	private RepositorioLibro repositorioLibro;
	
	public static void main(String[] args) {
		SpringApplication.run(RestTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Libro> libros = repositorioLibro.findAll();
		
		libros.forEach(System.out :: println);
	}

}
