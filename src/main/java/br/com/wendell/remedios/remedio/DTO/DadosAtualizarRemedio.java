package br.com.wendell.remedios.remedio.DTO;

import br.com.wendell.remedios.remedio.ENUMS.Laboratorio;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
        @NotNull
        long id,

        @NotBlank
        String nome,

        @Enumerated
        Laboratorio laboratorio
        ) {}
