package com.mytasks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytasks.controller.request.TarefaRequest;
import com.mytasks.controller.response.TarefaResponse;
import com.mytasks.model.Tarefa;
import com.mytasks.service.TarefaService;


//@CrossOrigin

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	@Autowired
	private TarefaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<TarefaResponse> todasTarefas() {
		List<Tarefa> tarefas = service.getTodasTarefas();

		List<TarefaResponse> tarefasResp = tarefas.stream()
			.map(tarefa ->  mapper.map(tarefa, TarefaResponse.class) )
			.collect(Collectors.toList());

		return tarefasResp;
	}
	
	@GetMapping("/{id}")
	public TarefaResponse umaTarefa( @PathVariable Integer id) {
		Tarefa tarefa = service.getTarefaPorId(id);
		
		return mapper.map(tarefa, TarefaResponse.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public TarefaResponse salvarTarefa(@RequestBody TarefaRequest tarefaReq) {
		Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);
		Tarefa tarefaSalva = service.salvarTarefa(tarefa);
		
		return mapper.map(tarefaSalva, TarefaResponse.class);
	}
	
	public TarefaResponse atualizarTarefa(
			@PathVariable Integer id,
			@RequestBody TarefaRequest tarefaReq) {
		
		Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);
		Tarefa tarefaAtualizada = service.atualizarTarefa(id, tarefa);
		return mapper.map(tarefaAtualizada, TarefaResponse.class);
	}
}
