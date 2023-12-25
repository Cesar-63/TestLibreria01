package com.testlibreria.resttest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testlibreria.resttest.entity.Libro;
import com.testlibreria.resttest.entity.Transaccion;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ServicioLibreria {
	
	@Autowired
	private RepositorioLibro repositorioLibro;
	
	@Autowired
	private RepositorioTransaccion repositorioTransaccion;
	
	@Transactional
	public void compraLibro(Long idLibro) {
		
		Libro libro = repositorioLibro.findById(idLibro).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));
		
		if (libro.getCantidadDisponible() > 0) {
			//Disminuye la cantidad de libros
			libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
			repositorioLibro.save(libro);
			
			//Registrar la transaccion
			Transaccion transaccion = new Transaccion();
			transaccion.setLibro(libro);
			transaccion.setFecha(LocalDateTime.now());
			repositorioTransaccion.save(transaccion);
		} else {
			throw new IllegalStateException("No hay copias disponibles de este libro");
		}
		
	}
	
	public List<Libro> getAllLibros(){
		return repositorioLibro.findAll();
	}

}
