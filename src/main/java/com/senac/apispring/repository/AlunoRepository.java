package com.senac.apispring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.apispring.aluno.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
