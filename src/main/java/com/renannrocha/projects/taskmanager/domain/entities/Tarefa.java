package com.renannrocha.projects.taskmanager.domain.entities;

import com.renannrocha.projects.taskmanager.domain.entities.enums.TaskStatus;
import lombok.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Tarefa implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String titulo;
    private String descricao;
    private TaskStatus status;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private Usuario usuario;
}
