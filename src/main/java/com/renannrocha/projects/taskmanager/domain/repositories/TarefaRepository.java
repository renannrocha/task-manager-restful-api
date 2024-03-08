package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Tarefa;
import com.renannrocha.projects.taskmanager.domain.entities.Usuario;

import java.util.List;

public interface TarefaRepository {
    public Tarefa save(Tarefa tarefa);

    public Tarefa update(Tarefa tarefa);

    public void delete(Tarefa tarefa);

    public void deleteById(Integer id);

    public List<Tarefa> list();

    public Tarefa findTarefaById(Integer id);
}
