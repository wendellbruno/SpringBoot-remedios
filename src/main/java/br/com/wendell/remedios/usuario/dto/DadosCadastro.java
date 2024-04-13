package br.com.wendell.remedios.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastro(
    @NotBlank
    String login,

    @NotBlank
    String senha
) {}
