package com.example.login_api.Token;

import com.example.login_api.Login.Login;
import com.example.login_api.Login.LoginReposytori;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private LoginReposytori loginReposytori;
    @Autowired
    public TokenService(LoginReposytori loginReposytori){
        this.loginReposytori = loginReposytori;
    }

    private String secret = "jfdgushjfusdhfsuhfsj123131231281uybsdfh178yfdhyiuhyuighduy1qgyasg6daft189yuhbfy8yadsbdyufb12uivbdfsuyfvuy12ifd";

    public TokenResonse gerarToken(String email, int expiraEmDias) {

        Date agora = new Date();

        Calendar expira = Calendar.getInstance();
        expira.add(Calendar.DAY_OF_MONTH, expiraEmDias);


        return new TokenResonse(Jwts.builder()
                .setSubject(email)
                .setIssuedAt(agora)
                .setIssuer(email)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(expira.getTime())
                .compact());
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Optional<Login> getUser(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        String id = body.getIssuer();
        return loginReposytori.findByEmail(id);
    }
}
