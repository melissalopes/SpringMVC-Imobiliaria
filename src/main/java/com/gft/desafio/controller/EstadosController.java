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
import com.gft.desafio.repository.EstadosRepository;

@Controller
@RequestMapping("/estados")
public class EstadosController {
	
	@Autowired
	private EstadosRepository repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("Estado/CadastroEstado");
		mv.addObject(new Estado());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Estado estado, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Nenhum campo pode estar nulo!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/estados/novo";
		}
		
		repository.save(estado);
		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/estados/novo";
	}
	
	@RequestMapping(value="/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Estado> listaEstados = repository.findAll();
		ModelAndView mv = new ModelAndView("Estado/PesquisaEstado");
		mv.addObject("todosEstados", listaEstados);
		return mv;
	}
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoEstado) {
		Estado estado = repository.getOne(codigoEstado);
		ModelAndView mv = new ModelAndView("Estado/CadastroEstado");
		mv.addObject(estado);
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoEstado, RedirectAttributes attributes) {
		repository.deleteById(codigoEstado);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/estados/pesquisa";
	}
	
	
	
	
}
