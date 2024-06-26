package ru.dnlkk.ratingusbackend.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AuthApi;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.service.AuthService;
import ru.dnlkk.ratingusbackend.api.dtos.JWTRequest;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@SecurityRequirements
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public ResponseEntity login(HttpServletResponse response, JWTRequest jwtRequest) {
        try {
            var token = authService.signIn(jwtRequest);
            response.addHeader("Set-Cookie", "token=" + token.getToken() + "; HttpOnly; Secure; SameSite=Strict");
            return ResponseEntity.ok(token.getToken());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity register(HttpServletResponse response, JWTRegistrationDto jwtRegistrationDto) {
        try {
            var token = authService.signUp(jwtRegistrationDto);
            response.addHeader("Set-Cookie", "token=" + token.getToken() + "; HttpOnly; Secure; SameSite=Strict");
            return ResponseEntity.ok(token.getToken());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
