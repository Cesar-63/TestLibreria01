package com.testlibreria.resttest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlibreria.resttest.entity.Transaccion;

public interface RepositorioTransaccion extends JpaRepository<Transaccion, Long>{

}
