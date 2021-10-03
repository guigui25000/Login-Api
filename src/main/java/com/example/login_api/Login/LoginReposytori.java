package com.example.login_api.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginReposytori extends JpaRepository<Login,Long> {

    Optional<Login> findByEmail(String s);
}
