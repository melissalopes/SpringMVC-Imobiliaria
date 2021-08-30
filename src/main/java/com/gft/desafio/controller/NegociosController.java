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

import com.gft.desafio.model.Negocio;
import com.gft.desafio.repository.NegociosRepository;

@Controller
@RequestMapping("/negocios")
public class NegociosController {
	
	@Autowired
	private NegociosRepository repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("Negocio/CadastroNegocio");
		mv.addObject(new Negocio());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Negocio negocio, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Negócio não pode ser nulo!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/negocios/novo";
		}
		
		repository.save(negocio);
		attributes.addFlashAttribute("mensagem", "Negócio salvo com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/negocios/novo";
	}
	
	@RequestMapping(value = "/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Negocio> listaNegocios = repository.findAll();
		ModelAndView mv = new ModelAndView("Negocio/PesquisaNegocio");
		mv.addObject("todosNegocios", listaNegocios);
		return mv;
	}
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoNegocio) {
		Negocio negocio = repository.getOne(codigoNegocio);
		ModelAndView mv = new ModelAndView("Negocio/CadastroNegocio");
		mv.addObject(negocio);
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoNegocio, RedirectAttributes attributes) {
		repository.deleteById(codigoNegocio);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/negocios/pesquisa";
	}
	
	
	
	
	
}
