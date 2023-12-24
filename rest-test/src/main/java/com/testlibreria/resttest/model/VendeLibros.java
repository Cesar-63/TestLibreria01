package com.testlibreria.resttest.model;

public class VendeLibros{
    private String isbn;
    private String titulo;
    private String autor;
    private String precio;
    
    
	public VendeLibros() {
		// TODO Auto-generated constructor stub
	}


	public VendeLibros(String isbn, String titulo, String autor, String precio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}
	

	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
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


	public String getPrecio() {
		return precio;
	}


	public void setPrecio(String precio) {
		this.precio = precio;
	}


}