package com.testlibreria.resttest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLibro;

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "precio")
	private int precio;
	
	@Column(name = "cantidad_disponible")
	private int cantidadDisponible;
	
	public Libro() {
		
	}
	
	public Libro(Long idLibro, String titulo, String autor, int precio, int cantidadDisponible) {
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.cantidadDisponible = cantidadDisponible;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio
				+ ", cantidadDisponible=" + cantidadDisponible + "]";
	}

	
}
