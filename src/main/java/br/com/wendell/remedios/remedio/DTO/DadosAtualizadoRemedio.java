package br.com.wendell.remedios.remedio.DTO;

import java.time.LocalDate;

import br.com.wendell.remedios.remedio.ENUMS.Laboratorio;
import br.com.wendell.remedios.remedio.ENUMS.Via;
import br.com.wendell.remedios.remedio.model.RemedioModel;

public record DadosAtualizadoRemedio(
    long id,

    String nome,
    
    Via via,

    String lote,

    int quantidade,

    LocalDate validade,

    Laboratorio laboratorio,

    Boolean ativo
) {
    public DadosAtualizadoRemedio(RemedioModel remedio) {
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getQuantidade(),
                remedio.getValidade(), remedio.getLaboratorio(), remedio.isAtivo());
    }
}
