package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
