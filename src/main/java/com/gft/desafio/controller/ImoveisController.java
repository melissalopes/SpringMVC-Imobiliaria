package com.gft.desafio.controller;

import java.util.List;
import java.util.Optional;

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
import com.gft.desafio.model.Categoria;
import com.gft.desafio.model.Estado;
import com.gft.desafio.model.Imovel;
import com.gft.desafio.model.Municipio;
import com.gft.desafio.model.Negocio;
import com.gft.desafio.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImoveisController {
	
	@Autowired
	private ImovelService imovelService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String inicio() {
		return "Home";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("Imovel/CadastroImovel");
		
		List<Categoria> listaCategorias = imovelService.listaCategoria();
		List<Negocio> listaNegocios = imovelService.listaNegocio();
		List<Estado> listaEstados = imovelService.listaEstado();
		List<Municipio> listaMunicipios = imovelService.listaMunicipio();
		List<Bairro> listaBairros = imovelService.listaBairro();
		
		mv.addObject("imovel", new Imovel());
		mv.addObject("todasCategorias", listaCategorias);
		mv.addObject("todosNegocios", listaNegocios);
		mv.addObject("todosEstados", listaEstados);
		mv.addObject("todosMunicipios", listaMunicipios);
		mv.addObject("todosBairros", listaBairros);
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Imovel imovel, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Nenhum campo pode estar nulo!");
			attributes.addFlashAttribute("alerta", "danger");
			return "redirect:/imoveis/novo";
		}
		
		imovelService.salvar(imovel);
		attributes.addFlashAttribute("mensagem", "Imóvel salvo com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/imoveis/novo";
	}
	
	@RequestMapping(value = "/pesquisa", method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("Imovel/PesquisaImovel");
		
		List<Imovel> listaImoveis = imovelService.listaImoveis();
		List<Categoria> listaCategorias = imovelService.listaCategoria();
		List<Negocio> listaNegocios = imovelService.listaNegocio();
		List<Estado> listaEstados = imovelService.listaEstado();
		List<Municipio> listaMunicipios = imovelService.listaMunicipio();
		List<Bairro> listaBairros = imovelService.listaBairro();	
		
		mv.addObject("todosImoveis", listaImoveis);		
		mv.addObject("todasCategorias", listaCategorias);
		mv.addObject("todosNegocios", listaNegocios);
		mv.addObject("todosEstados", listaEstados);
		mv.addObject("todosMunicipios", listaMunicipios);
		mv.addObject("todosBairros", listaBairros);
		return mv;
	}
	
	@RequestMapping(value="/pesquisa/{codigo}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") Long codigoImovel) {
		ModelAndView mv = new ModelAndView("Imovel/CadastroImovel");
		Optional<Imovel> imovel = imovelService.pesquisar(codigoImovel);
			
		if(imovel.isPresent()) {
			mv.addObject("imovel", imovel.get());
		}
		
		List<Categoria> listaCategorias = imovelService.listaCategoria();
		List<Negocio> listaNegocios = imovelService.listaNegocio();
		List<Estado> listaEstados = imovelService.listaEstado();
		List<Municipio> listaMunicipios = imovelService.listaMunicipio();
		List<Bairro> listaBairros = imovelService.listaBairro();
		mv.addObject("todasCategorias", listaCategorias);
		mv.addObject("todosNegocios", listaNegocios);
		mv.addObject("todosEstados", listaEstados);
		mv.addObject("todosMunicipios", listaMunicipios);
		mv.addObject("todosBairros", listaBairros);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigoImovel, RedirectAttributes attributes) {
		imovelService.excluir(codigoImovel);
		attributes.addFlashAttribute("mensagem", "Excluído com sucesso!");
		attributes.addFlashAttribute("alerta", "success");
		return "redirect:/imoveis/pesquisa";
	}
	
}
