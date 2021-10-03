package com.example.login_api.Login;

import com.example.login_api.Token.TokenResonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginControler {

    private final LoginService loginService;

    @Autowired
    public LoginControler(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/criar")
    public ResponseEntity<LoginDTO> criarConta(@RequestBody LoginRequest loginRequest){
        return loginService.criarLogin(loginRequest);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LoginDTO>> getContas(){
        return loginService.getContas();
    }

    @PostMapping("/acecar")
    public ResponseEntity<TokenResonse> fazerLogin(@RequestBody LoginRequest loginRequest){
        return loginService.fazerLogin(loginRequest);
    }
}
