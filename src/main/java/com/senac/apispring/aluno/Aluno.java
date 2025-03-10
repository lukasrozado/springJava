package com.senac.apispring.aluno;

import com.senac.apispring.endereco.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "alunos")
@Entity(name = "Aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String email;
    
    private String cpf;
    private String matricula;
    
    
    @Enumerated(EnumType.STRING)
    private  Curso Curso;
	
    
    private Aluno() {}
    
    private Endereco endereco;
    
    public Aluno(DadosCadastroAluno dados) {
    	this.setNome(dados.nome());
    	this.setEmail(dados.email());
    	this.setCpf(dados.cpf());
    	this.setMatricula(dados.matricula());
    	this.Curso = dados.curso();
    	this.setEndereco(new Endereco(dados.endereco()));

    }
	public void atualizarInformacoes(DadosAtualizacaoAluno dados){
		if(dados.nome() != null){
			this.nome = dados.nome();
		}
		if(dados.email() != null){
			this.email = dados.email();
		}
		if(dados.cpf() != null){
			this.cpf = dados.cpf();
		}
		if(dados.curso() !=null){
			this.Curso = dados.curso();
		}
		if(dados.endereco() !=null){
			this.endereco.atualizarInformacoes(dados.endereco());
		}

	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		
		return Curso;
	}
	
	public void SetCurso(Curso curso) {
		this.Curso = curso;
	}

	public Long getId(){
		return id;
	}
	public void SetId(long id){
		this.id = id;
	}
    
}
