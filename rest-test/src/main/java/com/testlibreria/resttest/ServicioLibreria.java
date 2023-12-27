package com.testlibreria.resttest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testlibreria.resttest.entity.Cliente;
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
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	@Transactional
	public void compraLibro(Long idLibro, Long idCliente) {
		
		Libro libro = repositorioLibro.findById(idLibro).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));
		Cliente cliente = repositorioCliente.findById(idCliente).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
		
		if (libro.getCantidadDisponible() > 0) {
			//Disminuye la cantidad de libros
			libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
			repositorioLibro.save(libro);
			
			//Registrar la transaccion
			Transaccion transaccion = new Transaccion();
			transaccion.setLibro(libro);
			transaccion.setFecha(LocalDateTime.now());
			transaccion.setCliente(cliente);
			repositorioTransaccion.save(transaccion);
		} else {
			throw new IllegalStateException("No hay copias disponibles de este libro");
		}
		
	}
	
	public void registraClienteNuevo(Cliente cliente) {
		
		repositorioCliente.save(cliente);
	}
	
	public List<Libro> getAllLibros(){
		return repositorioLibro.findAll();
	}

}
