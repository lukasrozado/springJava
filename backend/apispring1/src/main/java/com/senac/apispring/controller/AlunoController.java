package com.senac.apispring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.apispring.aluno.Aluno;
import com.senac.apispring.aluno.DadosAtualizacaoAluno;
import com.senac.apispring.aluno.DadosCadastroAluno;
import com.senac.apispring.aluno.DadosListagemAluno;
import com.senac.apispring.repository.AlunoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController

@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	


	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity<String> cadastrarAlunos(@RequestBody @Valid DadosCadastroAluno dados) {
		alunoRepository.save(new Aluno(dados));

		return new ResponseEntity<>("Aluno cadastrado com sucesso!", HttpStatus.OK);

	}
	

	@GetMapping("/listar")
	public List<DadosListagemAluno> listar(){
		return alunoRepository.findAllByAtivoTrue().stream().map(DadosListagemAluno::new).toList();
		}
	
		@GetMapping("/listar/{id}")
		public ResponseEntity<DadosListagemAluno> encontrarPorId(@PathVariable("id") Long id) {
			var aluno = alunoRepository.findById(id).map(DadosListagemAluno::new);
			return aluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

		}
	
	@PutMapping("/atualizar")
	@Transactional
	public void atualizar(@RequestBody DadosAtualizacaoAluno dados) {
		var aluno = alunoRepository.getReferenceById(dados.id());
		aluno.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public void excluir(@PathVariable("id") Long id) {
		 alunoRepository.deleteById(id);
	}
	
	@DeleteMapping("/deletar-logico/{id}")
	@Transactional
	public void excluirLogico(@PathVariable("id") Long id) {
	
		var aluno = alunoRepository.getReferenceById(id);
		aluno.excluir();
	}
	
	

}
