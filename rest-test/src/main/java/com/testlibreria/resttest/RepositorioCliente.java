package com.testlibreria.resttest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlibreria.resttest.entity.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNombreContaining(String nombre);
}
