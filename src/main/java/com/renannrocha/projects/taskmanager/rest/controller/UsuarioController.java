package com.renannrocha.projects.taskmanager.rest.controller;

import com.renannrocha.projects.taskmanager.domain.entities.Usuario;
import com.renannrocha.projects.taskmanager.domain.repositories.implementation.UsuarioRepositoryImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    private UsuarioRepositoryImpl repository;

    public UsuarioController(UsuarioRepositoryImpl repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUsuarioByID (@PathVariable Integer id){
        try{
            Usuario usuario = repository.findUsuarioById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listClientes(){
        List<Usuario> usuarios = repository.list();
        if(usuarios.isEmpty()){
            return new ResponseEntity<>("Erro - não há usuários cadastrados", HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarUsuarioPorId(@PathVariable Integer id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>("usuario deletado com sucesso !", HttpStatus.GONE);
        } else {
            return new ResponseEntity<>("erro - valor de id incorreto ou não há usuarios", HttpStatus.BAD_REQUEST);
        }
    }

}

