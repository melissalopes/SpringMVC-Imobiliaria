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

import com.gft.desafio.model.Bairro;
import com.gft.desafio.model.Municipio;
import com.gft.desafio.repository.BairrosRepository;
import com.gft.desafio.repository.MunicipiosRepository;

@Controller
@RequestMapping("/bairros")
public class BairrosController {
	
	@Autowired
	private BairrosRepository repository;
	
	@Autowired
	private MunicipiosRepository municipiosRepository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		List<Municipio> listaMunicipios = municipiosRepository.findAll();
		
		ModelAndView mv = new ModelAndView("Bairro/CadastroBairro");
		mv.addObject(new Bairro());
		mv.addObject("todosMunicipios", listaMunicipios);
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Bairro bairro, Errors errors, RedirectAttributes attributes) {
		List<Municipio> listaMunicipios = municipiosRepository.findAll();
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Bairro não pode ser nulo!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/bairros/novo";
			
	    }else if(!listaMunicipios.isEmpty()) {
			repository.save(bairro);
			attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso!");
			attributes.addFlashAttribute("alerta", "success");	
	    }else {
			attributes.addFlashAttribute("mensagem", "Não há como cadastrar um Bairro sem um Município!");
			attributes.addFlashAttribute("alerta", "danger");
		
	    }
		
		return "redirect:/bairros/novo";
	}
	
	@RequestMapping(value="/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Bairro> listaBairros = repository.findAll();
		ModelAndView mv = new ModelAndView("Bairro/PesquisaBairro");
		mv.addObject("todosBairros", listaBairros);
		return mv;
	}
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoBairro) {
		Bairro bairro = repository.getOne(codigoBairro);
		List<Municipio> listaMunicipios = municipiosRepository.findAll();
		ModelAndView mv = new ModelAndView("Bairro/CadastroBairro");
		mv.addObject(bairro);
		mv.addObject("todosMunicipios", listaMunicipios);
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoBairro, RedirectAttributes attributes) {
		repository.deleteById(codigoBairro);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/bairros/pesquisa";
	}
	
}
