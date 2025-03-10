package com.senac.apispring.aluno;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Professor{
    private Long id;
    private String nome;
    private String email;

    private String cpf;
    private String matricula;
    private  Curso Curso;
}
