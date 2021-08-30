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

import com.gft.desafio.model.Categoria;
import com.gft.desafio.repository.CategoriasRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	private CategoriasRepository repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("Categoria/CadastroCategoria");
		mv.addObject(new Categoria());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Categoria não pode ser nula!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/categorias/novo";
		}
		
		repository.save(categoria);
		attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/categorias/novo";
	}
	
	@RequestMapping(value = "/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Categoria> listaCategorias = repository.findAll();
		ModelAndView mv = new ModelAndView("Categoria/PesquisaCategoria");
		mv.addObject("todasCategorias", listaCategorias);
		return mv;
	}
	
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoCategoria) {		
		Categoria categoria = repository.getOne(codigoCategoria);
		ModelAndView mv = new ModelAndView("Categoria/CadastroCategoria");
		mv.addObject(categoria);                              
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoCategoria, RedirectAttributes attributes) {  
		repository.deleteById(codigoCategoria);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/categorias/pesquisa";
	}
}
