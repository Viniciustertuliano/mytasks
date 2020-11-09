package com.mytasks.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mytasks.model.Tarefa;
import com.mytasks.model.TarefaCategoria;
import com.mytasks.repository.TarefaCategoriaRepository;
import com.mytasks.repository.TarefaRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

	@Autowired
	private TarefaCategoriaRepository categoriaRepo;

	@Autowired
	private TarefaRepository tarefaRepo;

	@Bean
	CommandLineRunner execute() {
		return args -> {

			TarefaCategoria categoriaProfissional = new TarefaCategoria();
			categoriaProfissional.setNome("Profissional");
			categoriaRepo.save(categoriaProfissional);

			TarefaCategoria categoriaPessoal = new TarefaCategoria();
			categoriaPessoal.setNome("Pessoal");
			categoriaRepo.save(categoriaPessoal);

			Tarefa tarefa1 = new Tarefa();
			tarefa1.setTitulo("Estudar Java e Spring Boot");
			tarefa1.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa1.setCategoria(categoriaProfissional);
			tarefaRepo.save(tarefa1);

			Tarefa tarefa2 = new Tarefa();
			tarefa2.setTitulo("Passear com a Cecilia");
			tarefa2.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa2.setCategoria(categoriaPessoal);
			tarefaRepo.save(tarefa2);


		};
	}

}