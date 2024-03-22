package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

}
