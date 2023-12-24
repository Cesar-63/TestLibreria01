package com.testlibreria.resttest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testlibreria.resttest.model.VendeLibros;

@RestController
@RequestMapping("/vendelibros")
public class BookAPIService {

	@GetMapping("{isbn}")
	public VendeLibros getDetallesVendeLibros(String isbn) {
		return new VendeLibros("463", "Harry Potter 1", 
				"JK Rowling", "10");
	}
}
