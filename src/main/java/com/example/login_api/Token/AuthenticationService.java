package com.example.login_api.Token;


import com.example.login_api.Login.LoginReposytori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {


    private LoginReposytori usuarioRepository;

    @Autowired
    public AuthenticationService(LoginReposytori repository){
        usuarioRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));
    }
}
