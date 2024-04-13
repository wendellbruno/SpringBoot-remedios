package br.com.wendell.remedios.remedio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.wendell.remedios.remedio.ENUMS.Via;

import java.time.LocalDate;

import br.com.wendell.remedios.remedio.DTO.DadosAtualizarRemedio;
import br.com.wendell.remedios.remedio.DTO.DadosCadastroRemedio;
import br.com.wendell.remedios.remedio.ENUMS.Laboratorio;

@Table(name = "Remedios")
@Entity(name = "remedios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RemedioModel {


    public RemedioModel(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.via = dados.via();
        this.laboratorio = dados.laboratorio();
        this.ativo = true;
    }



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    private String lote;
    private int quantidade;
    private LocalDate validade;

    @Enumerated(EnumType.STRING)
    private Via via;
    
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    private boolean ativo;

    public void atualizarRemedio(@Valid DadosAtualizarRemedio dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.laboratorio() != null){
            this.laboratorio = dados.laboratorio();
        }
    }

    public void inativar(Long id) {
        this.ativo = false;
    }

    public void ativar(Long id) {
        this.ativo = true;
    }
    
}
