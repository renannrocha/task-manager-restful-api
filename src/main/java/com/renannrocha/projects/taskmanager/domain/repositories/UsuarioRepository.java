package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
