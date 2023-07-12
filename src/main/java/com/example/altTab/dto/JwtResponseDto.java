package com.example.altTab.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
