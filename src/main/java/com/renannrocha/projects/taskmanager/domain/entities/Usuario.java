package com.renannrocha.projects.taskmanager.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Usuario implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String senha;
}
