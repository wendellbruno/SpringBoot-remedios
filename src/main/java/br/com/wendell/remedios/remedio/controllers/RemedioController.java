package br.com.wendell.remedios.remedio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.wendell.remedios.remedio.DTO.DadosAtualizadoRemedio;
import br.com.wendell.remedios.remedio.DTO.DadosAtualizarRemedio;
import br.com.wendell.remedios.remedio.DTO.DadosCadastroRemedio;
import br.com.wendell.remedios.remedio.DTO.DadosListagemRemedio;
import br.com.wendell.remedios.remedio.model.RemedioModel;
import br.com.wendell.remedios.remedio.repository.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    /* Intancia a classe uma unica vez */
    @Autowired
    private RemedioRepository repository;
    
    @PostMapping
    @Transactional //faz rollback caso aconteça algum erro na transação.
    public ResponseEntity<DadosAtualizadoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder){
        var remedio = new RemedioModel(dados);
        repository.save(remedio);
        //vai mandar a location no header da respostar ao criar o remedio
        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAtualizadoRemedio(remedio));
    };

    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar(){
        var list = repository.findByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    };

    @GetMapping("/{id}")
    public ResponseEntity<DadosAtualizadoRemedio> pegarPorId(@PathVariable(value = "id") Long id){
        var remedio = repository.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosAtualizadoRemedio(remedio));
    }

    @PutMapping
    @Transactional //faz rollback caso aconteça algum erro na transação
    public ResponseEntity<DadosAtualizadoRemedio> atualizarRemedio(@RequestBody @Valid DadosAtualizarRemedio dados ){
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarRemedio(dados);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosAtualizadoRemedio(remedio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarRemedio(@PathVariable(value = "id") Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativarRemedio(@PathVariable(value = "id") Long id){
        var remedio = repository.getReferenceById(id);
        remedio.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativarRemedio(@PathVariable(value="id") Long id){
        var remedio = repository.getReferenceById(id);
        remedio.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
