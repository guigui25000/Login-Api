package com.example.login_api.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String email;

    public LoginDTO(Login login){
        this.email = login.getEmail();
    }

    public LoginDTO(LoginRequest login){
        this.email = login.getEmail();
    }


}
