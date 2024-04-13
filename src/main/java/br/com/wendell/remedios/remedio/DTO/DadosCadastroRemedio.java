package br.com.wendell.remedios.remedio.DTO;

import br.com.wendell.remedios.remedio.ENUMS.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import br.com.wendell.remedios.remedio.ENUMS.Laboratorio;

public record DadosCadastroRemedio(
    @NotBlank
    String nome,
    
    @Enumerated
    Via via,

    @NotBlank
    String lote,

    @NotNull
    int quantidade,

    @Future //indepe que a validade seja menor que o dia atual
    LocalDate validade,

    @Enumerated
    Laboratorio laboratorio
) {}
