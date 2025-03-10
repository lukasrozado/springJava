package com.senac.apispring.controller;

import java.util.List;

import com.senac.apispring.aluno.DadosAtualizacaoAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senac.apispring.aluno.Aluno;
import com.senac.apispring.aluno.DadosCadastroAluno;
import com.senac.apispring.aluno.DadosListagemAluno;
import com.senac.apispring.repository.AlunoRepository;

import jakarta.transaction.Transactional;


@RestController //<= Arquivo Tipo Controller
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@PostMapping("/cadastro")
	@Transactional
	public void cadastrarAlunos(@RequestBody DadosCadastroAluno dados) {
		alunoRepository.save(new Aluno(dados));
	}
	
	
	@GetMapping("/listar")
	public List<DadosListagemAluno> listar(){
		return alunoRepository.findAll().stream().map(DadosListagemAluno::new).toList();
		}
	@PutMapping("/atualizar")
	@Transactional
	public void atualizar(@RequestBody DadosAtualizacaoAluno dados){
		var aluno = alunoRepository.getReferenceById(dados.id());
		aluno.atualizarInformacoes(dados);
	}
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public void excluir(@PathVariable("id") long id){
		alunoRepository.deleteById(id);
	}
	

}
