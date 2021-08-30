package com.gft.desafio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafio.model.Imovel;


public interface ImoveisRepository extends JpaRepository<Imovel, Long> {

}
