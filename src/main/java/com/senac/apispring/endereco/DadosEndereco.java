package com.senac.apispring.endereco;

public record DadosEndereco(
		
		String logradouro,
		String uf,
		String cep,
		String numero,
		String complemento,
		String cidade,
		String bairro
		) {

}
