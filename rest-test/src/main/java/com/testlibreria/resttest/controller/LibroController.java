package com.testlibreria.resttest.controller;

import com.testlibreria.resttest.Libro;
import com.testlibreria.resttest.RepositorioLibro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LibroController {
	@Autowired
	RepositorioLibro repositorioLibro;

	@GetMapping("/libros")
	public ResponseEntity<List<Libro>> getAllLibros(@RequestParam(required = false) String titulo) {
		try {
			List<Libro> libros = new ArrayList<Libro>();

			if (titulo == null)
				repositorioLibro.findAll().forEach(libros::add);
			else
				repositorioLibro.findByTituloContaining(titulo).forEach(libros::add);

			if (libros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(libros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/libros/{isbn}")
	public ResponseEntity<Libro> getLibroByIsbn(@PathVariable("isbn") long isbn) {
		Optional<Libro> libroData = repositorioLibro.findById(isbn);

		if (libroData.isPresent()) {
			return new ResponseEntity<>(libroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/libros")
	public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
		try {
			Libro _libro = repositorioLibro
					.save(new Libro(libro.getTitulo(), libro.getAutor(), libro.getPrecio()));
			return new ResponseEntity<>(_libro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/libros/{isbn}")
	public ResponseEntity<Libro> updateLibro(@PathVariable("isbn") long isbn, @RequestBody Libro libro) {
		Optional<Libro> libroData = repositorioLibro.findById(isbn);

		if (libroData.isPresent()) {
			Libro _libro = libroData.get();
			_libro.setTitulo(libro.getTitulo());
			_libro.setAutor(libro.getAutor());
			_libro.setPrecio(libro.getPrecio());
			return new ResponseEntity<>(repositorioLibro.save(_libro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/libros/{isbn}")
	public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("isbn") long isbn) {
		try {
			repositorioLibro.deleteById(isbn);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/libros")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			repositorioLibro.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
