package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.auth.AuthenticationResponse;
import com.turkcell.spring.starter.entities.auth.LoginRequest;
import com.turkcell.spring.starter.entities.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}