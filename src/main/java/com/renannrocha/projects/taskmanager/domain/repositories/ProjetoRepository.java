package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Projeto;

import java.util.List;

public interface ProjetoRepository {
    public Projeto save(Projeto projeto);

    public Projeto update(Projeto projeto);

    public void delete(Projeto projeto);

    public void deleteById(Integer id);

    public List<Object> list();
}
