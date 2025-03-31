package com.senac.apispring.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUser(@NotBlank String nome,

		@NotBlank @Email String email,

		@NotNull Role role, @NotNull String password) {


}
