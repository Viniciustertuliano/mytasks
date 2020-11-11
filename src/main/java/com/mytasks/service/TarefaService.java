package com.mytasks.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytasks.model.Tarefa;
import com.mytasks.model.TarefaStatus;
import com.mytasks.repository.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository repository;
	
	
	public Tarefa concluirTarefaPorId(Integer id) {
		
		Tarefa tarefa = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (TarefaStatus.CANCELADO.equals(tarefa.getStatus())) 
			throw new IllegalStateException();
		
		tarefa.setStatus(TarefaStatus.CONCLUIDO);
		return repository.save(tarefa);
	}
	
	public List<Tarefa> getTodasTarefas(){
		return repository.findAll();
	}
	
	
	public Tarefa getTarefaPorId(Integer id){
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Tarefa salvarTarefa(Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	public Tarefa atualizarTarefa(Integer id, Tarefa tarefa) {
		if (!repository.existsById(id) )
			throw new EntityNotFoundException();
		
		tarefa.setId(id);
		return salvarTarefa(tarefa);
	}
	
}
