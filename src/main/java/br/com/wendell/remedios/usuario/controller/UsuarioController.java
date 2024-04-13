package br.com.wendell.remedios.usuario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wendell.remedios.usuario.dto.DadosCadastro;
import br.com.wendell.remedios.usuario.model.UsuarioModel;
import br.com.wendell.remedios.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository; 

    @PostMapping()
    @Transactional
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid DadosCadastro usuario) {
        var newUsuario = new UsuarioModel(usuario.login(), usuario.senha());
        repository.save(newUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
        
    }
    
    
}
