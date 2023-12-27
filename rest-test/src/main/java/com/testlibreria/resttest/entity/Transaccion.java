package com.testlibreria.resttest.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transaccion")
public class Transaccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaccion;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name = "idLibro")
	private Libro libro;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Transaccion() {
		
	}

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	
	

}
