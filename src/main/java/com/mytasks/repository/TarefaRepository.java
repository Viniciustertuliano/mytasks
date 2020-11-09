package com.mytasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytasks.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{

}
