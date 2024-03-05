package com.renannrocha.projects.taskmanager.domain.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Projeto implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;
    private Usuario usuario;
    private Set<Tarefa> tarefas;
}
