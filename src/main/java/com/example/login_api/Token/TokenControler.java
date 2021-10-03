package com.example.login_api.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validacao")
public class TokenControler {

    private TokenService tokenService;
    @Autowired
    public TokenControler(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<Boolean> validarToken(@RequestHeader String token){
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

}
