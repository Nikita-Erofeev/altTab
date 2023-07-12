package com.example.altTab.controller;

import com.example.altTab.dto.JwtRequestDto;
import com.example.altTab.dto.JwtResponseDto;
import com.example.altTab.exception.BaseException;
import com.example.altTab.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager; //check data for log in
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder; //TODO после того, как пользователь будет из бд, удалить

    @Autowired
    public AuthController(JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager, InMemoryUserDetailsManager inMemoryUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("api/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto) {
//        TODO Проверка, корректны ли логин и пароль (если из бд)
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<>(
//              new BaseException(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"),HttpStatus.UNAUTHORIZED
//            );
//        }
//        Проверка корректности логина и пароля in memory
//        TODO перенести все в сервис
        try {
            UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(jwtRequestDto.getUsername());
            if (passwordEncoder.matches(jwtRequestDto.getPassword(), userDetails.getPassword())) {
                String token = jwtTokenUtils.generateToken(userDetails);
//                TODO добавлять токен в header, а не возвращать просто JSON, хранить его в куках
                return ResponseEntity.ok(new JwtResponseDto(token));
            } else {
                throw new UsernameNotFoundException("Wrong password");
            }
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(
                    new BaseException(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"),HttpStatus.UNAUTHORIZED
            );
        }




    }

// TODO сохранение токина в header ответа. Подумать, как лучше
    /*@Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (Objects.nonNull(token)) {
            if (!response.getHeaderNames().contains(ACCESS_CONTROL_EXPOSE_HEADERS)){
                response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, token.getHeaderName());
            }
            if (response.getHeaderNames().contains(token.getHeaderName())) {
                response.setHeader(token.getHeaderName(), token.getToken());
            } else {
                response.addHeader(token.getHeaderName(), token.getToken());
            }
        }
    }*/

}
