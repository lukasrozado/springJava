package com.senac.apispring.aluno;


import com.senac.apispring.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp="\\d{11}")
        @NotNull
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco,
        @NotNull
        @Valid
        String password) {

}


