package com.example.login_api.Login;

import com.example.login_api.Token.TokenResonse;
import com.example.login_api.Token.TokenService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private final LoginReposytori loginReposytori;
    private final TokenService tokenService;

    @Autowired
    public LoginService(LoginReposytori loginReposytori, TokenService tokenService){
        this.loginReposytori = loginReposytori;
        this.tokenService = tokenService;
    }


    public ResponseEntity<TokenResonse> fazerLogin(LoginRequest loginRequest) {
        try {
            if(loginReposytori.findByEmail(loginRequest.getEmail()).isPresent()){
                if (!loginRequest.getSenha().equals(loginReposytori.findByEmail(loginRequest.getEmail()).get().getSenha())) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
                return ResponseEntity.status(HttpStatus.OK).body(tokenService.gerarToken(loginRequest.getEmail(),1));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<LoginDTO> criarLogin(LoginRequest loginRequest) {
        if(loginReposytori.findByEmail(loginRequest.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginDTO(loginRequest));
        }
        loginReposytori.save(montarLogin(loginRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginDTO(loginRequest));
    }

    public Login montarLogin(LoginRequest loginRequest){
        Login login = new Login();
        login.setEmail(loginRequest.getEmail());
        login.setSenha(loginRequest.getSenha());
        return login;
    }

    public ResponseEntity<List<LoginDTO>> getContas() {
        return ResponseEntity.status(HttpStatus.OK).body(loginReposytori.findAll().stream()
                .map(this::of)
                .collect(Collectors.toList())
        );
    }

    public LoginDTO of(Login login){
        return new LoginDTO(login);
    }
}
