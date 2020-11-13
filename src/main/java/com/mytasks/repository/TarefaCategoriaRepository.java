package com.mytasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytasks.model.TarefaCategoria;

public interface TarefaCategoriaRepository 
	extends JpaRepository<TarefaCategoria, Integer> {

	List<TarefaCategoria> findByNome(String nome);
	
}
