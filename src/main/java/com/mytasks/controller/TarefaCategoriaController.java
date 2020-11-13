package com.mytasks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytasks.controller.request.TarefaCategoriaRequest;
import com.mytasks.controller.response.TarefaCategoriaResponse;
import com.mytasks.model.TarefaCategoria;
import com.mytasks.service.TarefaCategoriaService;

@RestController
@RequestMapping("/categoria")
public class TarefaCategoriaController {

	@Autowired
	private TarefaCategoriaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<TarefaCategoriaResponse> todasCategorias() {
		List<TarefaCategoria> categorias = service.getTodasCategorias();
		return categorias
			.stream()
			.map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class))
			.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public TarefaCategoriaResponse umaCategoria(@PathVariable Integer id) {
		return mapper.map(
				service.getCategoriaPorId(id), 
				TarefaCategoriaResponse.class);
	}
	
	@PostMapping
	public TarefaCategoriaResponse salvarCategoria(@RequestBody TarefaCategoriaRequest categoriaReq) {
		TarefaCategoria categoria = mapper.map(categoriaReq, TarefaCategoria.class);
		return mapper.map(service.salvar(categoria), TarefaCategoriaResponse.class);
	}
	
	@PutMapping("/{id}")
	public TarefaCategoriaResponse atualizarCategoria(
			@PathVariable Integer id,
			@RequestBody TarefaCategoriaRequest categoriaReq) {
		
		TarefaCategoria categoria = mapper.map(categoriaReq, TarefaCategoria.class);
		return mapper.map(service.atualizar(id, categoria), TarefaCategoriaResponse.class);
	}
	
	@DeleteMapping("/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
}
