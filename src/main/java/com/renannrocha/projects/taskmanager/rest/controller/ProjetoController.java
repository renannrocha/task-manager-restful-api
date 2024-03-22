package com.renannrocha.projects.taskmanager.rest.controller;

import com.renannrocha.projects.taskmanager.domain.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;
}
