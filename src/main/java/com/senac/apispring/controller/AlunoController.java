package com.senac.apispring.controller;

import java.util.List;

import com.senac.apispring.aluno.DadosAtualizacaoAluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> cadastrarAlunos(@RequestBody @Valid DadosCadastroAluno dados) {
		alunoRepository.save(new Aluno(dados));
		return new ResponseEntity<>("Aluno cadastrado com sucesso!", HttpStatus.OK);
	}
	
	
	@GetMapping("/listar")
	public List<DadosListagemAluno> listar(){
		return alunoRepository.findAllByAtivoTrue().stream().map(DadosListagemAluno::new).toList();
		}
	@GetMapping("/listar/id")
	public ResponseEntity<DadosListagemAluno> encontrarPorId(@PathVariable("id") Long id){
		var aluno = alunoRepository.findById(id).map(DadosListagemAluno::new);
		return aluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
	@DeleteMapping("/deletar-logico/")
	@Transactional
	public void excluirLogico(@PathVariable("id") long id){
		var aluno = alunoRepository.getReferenceById(id);
		aluno.excluir();
	}

}
