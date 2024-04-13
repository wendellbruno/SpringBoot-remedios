package br.com.wendell.remedios.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wendell.remedios.infra.TokenService;
import br.com.wendell.remedios.usuario.dto.DadosAutentificacao;
import br.com.wendell.remedios.usuario.dto.DadosTokenJWT;
import br.com.wendell.remedios.usuario.model.UsuarioModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping

    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutentificacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = authenticationManager.authenticate(token);
        var tokenAuth = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenAuth));
    }


}
