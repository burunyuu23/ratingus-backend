package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.dtos.JWTResponseDto;
import ru.dnlkk.ratingusbackend.api.dtos.UserLoginDto;
import ru.dnlkk.ratingusbackend.api.dtos.UserRegistrationDto;

@Tag(name = "Контроллер авторизации", description = "Авторизация, регистрация")
@RequestMapping("/auth")
public interface AuthApi {
    @Operation(
            summary = "Удаление токена",
            description = "Удаляет JWT-токен из системы и ничего не возвращает"
    )
    @GetMapping("/logout")
    ResponseEntity<Void> logout();


    @Operation(
            summary = "Вход",
            description = "Возвращает JWT-токен для переданных логина и пароля"
    )
    @GetMapping("/login")
    ResponseEntity<JWTResponseDto> login(@RequestBody UserLoginDto userLoginDto);


    @Operation(
            summary = "Регистрация",
            description = "Создаёт пользователя и возвращает JWT-токен"
    )
    @GetMapping("/register")
    ResponseEntity<JWTResponseDto> register(@RequestBody UserRegistrationDto userRegistrationDto);
}
