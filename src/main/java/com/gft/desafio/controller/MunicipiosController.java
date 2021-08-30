package com.gft.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafio.model.Estado;
import com.gft.desafio.model.Municipio;
import com.gft.desafio.repository.EstadosRepository;
import com.gft.desafio.repository.MunicipiosRepository;

@Controller
@RequestMapping("/municipios")
public class MunicipiosController {
	
	@Autowired
	private MunicipiosRepository repository;
	
	@Autowired
	private EstadosRepository estadosRepository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		List<Estado> listaEstados = estadosRepository.findAll();
		
		ModelAndView mv = new ModelAndView("Municipio/CadastroMunicipio");
		mv.addObject(new Municipio());
		mv.addObject("todosEstados", listaEstados);
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Municipio municipio, Errors errors, RedirectAttributes attributes) {
		List<Estado> listaEstados = estadosRepository.findAll();
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Município não pode ser nulo!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/municipios/novo";
			
		}else if (!listaEstados.isEmpty()){	
			repository.save(municipio);
			attributes.addFlashAttribute("mensagem", "Município salvo com sucesso!");
			attributes.addFlashAttribute("alerta", "success");
		}else {
			attributes.addFlashAttribute("mensagem", "Não há como cadastrar um Município sem um Estado!");
			attributes.addFlashAttribute("alerta", "danger");
		}
		
		return "redirect:/municipios/novo";
	}
	
	@RequestMapping(value="/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Municipio> listaMunicipio = repository.findAll();
		ModelAndView mv = new ModelAndView("Municipio/PesquisaMunicipio");
		mv.addObject("todosMunicipios", listaMunicipio);
		return mv;
	}
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoMunicipio) {
		Municipio municipio = repository.getOne(codigoMunicipio);
		ModelAndView mv = new ModelAndView("Municipio/CadastroMunicipio");
		mv.addObject(municipio);		
		List<Estado> listaEstados = estadosRepository.findAll();
		mv.addObject("todosEstados", listaEstados);	
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoMunicipio, RedirectAttributes attributes) {
		repository.deleteById(codigoMunicipio);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/municipios/pesquisa";
	}
}
