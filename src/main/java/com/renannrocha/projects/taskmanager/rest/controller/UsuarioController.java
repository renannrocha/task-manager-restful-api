package com.renannrocha.projects.taskmanager.rest.controller;

import com.renannrocha.projects.taskmanager.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

}

