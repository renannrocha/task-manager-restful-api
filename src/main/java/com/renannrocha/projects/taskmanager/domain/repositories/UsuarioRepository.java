package com.renannrocha.projects.taskmanager.domain.repositories;

import com.renannrocha.projects.taskmanager.domain.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {

    public Usuario save(Usuario usuario);

    public Usuario update(Usuario usuario);

    public void delete(Usuario usuario);

    public void deleteById(Integer id);

    public List<Usuario> list();

    public Usuario findUsuarioById(Integer id);

}
