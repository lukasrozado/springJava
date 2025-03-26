package com.senac.apispring.aluno;

public record DadosListagemAluno(
		Long id,
		String nome,
		String email,
		String cpf,
		String matricula,
		Curso curso
		
		) {
	public DadosListagemAluno(Aluno aluno) {
		this( aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getMatricula(), aluno.getCurso());
	}

}
