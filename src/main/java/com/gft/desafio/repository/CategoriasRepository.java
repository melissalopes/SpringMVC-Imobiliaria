package com.gft.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafio.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>{
	 
	
}
