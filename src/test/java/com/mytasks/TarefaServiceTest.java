package com.mytasks;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mytasks.model.Tarefa;
import com.mytasks.model.TarefaStatus;
import com.mytasks.repository.TarefaRepository;
import com.mytasks.service.TarefaService;


@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	@Mock
	private TarefaRepository repository;
	
	@InjectMocks
	private TarefaService service;

	@Test
	private void naoDeixaConcluirTarefaCancelada() {
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo("Estudar Java");
		tarefa.setStatus(TarefaStatus.CANCELADO);
		
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(IllegalStateException.class,() -> service.concluirTarefaPorId(99));

	}
}
