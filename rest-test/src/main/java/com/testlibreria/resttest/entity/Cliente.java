package com.testlibreria.resttest.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCliente;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transaccion> transacciones;
	
	
	public Cliente() {
		
	}


	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Transaccion> getTransacciones() {
		return transacciones;
	}


	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	
	
	
}
