package com.senac.apispring.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		@NotBlank
		String logradouro,
		@NotBlank
		String uf,
		String cep,
		String numero,
		String complemento,
		@NotBlank
		String cidade,
		@NotBlank
		String bairro
		) {

}
