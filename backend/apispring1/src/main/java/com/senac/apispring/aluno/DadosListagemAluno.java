package com.senac.apispring.aluno;

import com.senac.apispring.endereco.Endereco;

public record DadosListagemAluno(
		Long id,
		String nome,
		String email,
		String cpf,
		String matricula,
		Curso curso,
		Endereco endereco

		) {
	public DadosListagemAluno(Aluno aluno) {
		this( aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getMatricula(), aluno.getCurso(), aluno.getEndereco());
	}

}
