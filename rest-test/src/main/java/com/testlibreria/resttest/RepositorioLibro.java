package com.testlibreria.resttest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlibreria.resttest.entity.Libro;

public interface RepositorioLibro extends JpaRepository<Libro, Long> {
	
	List<Libro> findByTituloContaining(String titulo);
	
	
}
