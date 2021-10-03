package com.example.login_api.Token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResonse {
    private String token;

    public TokenResonse(String token){
        this.token = token;
    }
}
