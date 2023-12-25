package com.testlibreria.resttest.controller;

import com.testlibreria.resttest.RepositorioLibro;
import com.testlibreria.resttest.ServicioLibreria;
import com.testlibreria.resttest.entity.Libro;

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
	
	@Autowired
	private ServicioLibreria servicioLibreria;

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
	public ResponseEntity<Libro> getLibroById(@PathVariable("idLibro") long idLibro) {
		Optional<Libro> libroData = repositorioLibro.findById(idLibro);

		if (libroData.isPresent()) {
			return new ResponseEntity<>(libroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/compraLibro/{idLibro}")
    public ResponseEntity<String> compraLibro(@PathVariable Long idLibro) {
        try {
            servicioLibreria.compraLibro(idLibro);
            return ResponseEntity.ok("Compra de Libro Exitosa!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }

	//@PostMapping("/libros")
	//public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
	//	try {
	//		Libro _libro = repositorioLibro
	//				.save(new Libro(null, libro.getTitulo(), libro.getAutor(), libro.getPrecio(), 10));
	//		return new ResponseEntity<>(_libro, HttpStatus.CREATED);
	//	} catch (Exception e) {
	//		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	//	}
	//}

	@PutMapping("/libros/{idLibro}")
	public ResponseEntity<Libro> updateLibro(@PathVariable("idLibro") long idLibro, @RequestBody Libro libro) {
		Optional<Libro> libroData = repositorioLibro.findById(idLibro);

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

	@DeleteMapping("/libros/{idLibro}")
	public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("idLibro") long idLibro) {
		try {
			repositorioLibro.deleteById(idLibro);
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
