package com.gft.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.model.Bairro;
import com.gft.desafio.model.Categoria;
import com.gft.desafio.model.Estado;
import com.gft.desafio.model.Imovel;
import com.gft.desafio.model.Municipio;
import com.gft.desafio.model.Negocio;
import com.gft.desafio.repository.BairrosRepository;
import com.gft.desafio.repository.CategoriasRepository;
import com.gft.desafio.repository.EstadosRepository;
import com.gft.desafio.repository.ImoveisRepository;
import com.gft.desafio.repository.MunicipiosRepository;
import com.gft.desafio.repository.NegociosRepository;

@Service
public class ImovelService {
	@Autowired
	private ImoveisRepository imovelRepository;
	
	@Autowired
	private EstadosRepository estadosRepository;
	
	@Autowired
	private MunicipiosRepository municipiosRepository;
	
	@Autowired
	private BairrosRepository bairrosRepository;
	
	@Autowired
	private CategoriasRepository categoriaRepository;
	
	@Autowired
	private NegociosRepository negocioRepository;
	
	public void salvar(Imovel imovel) {
		imovelRepository.save(imovel);
	}
	
	public List<Imovel> listaImoveis(){	
		return this.imovelRepository.findAll();
	}
	
	public List<Categoria> listaCategoria(){
		return this.categoriaRepository.findAll();
	}
	
	public List<Negocio> listaNegocio(){
		return this.negocioRepository.findAll();
	}
	
	public List<Estado> listaEstado(){
		return this.estadosRepository.findAll();
	}
	public List<Municipio> listaMunicipio(){
		return this.municipiosRepository.findAll();
	}
	public List<Bairro> listaBairro(){
		return this.bairrosRepository.findAll();
	}
	
	public Optional<Imovel> pesquisar(Long codigoImovel) {
		return imovelRepository.findById(codigoImovel);
	}

	public void excluir(Long codigo) {
		imovelRepository.deleteById(codigo);	
	}
	
	
}
