package com.example.altTab.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("classpath:application.properties")
public class TgBotConfig {
    @Value("${tgBot.name}")
    String botName;
    @Value("${tgBot.token}")
    String token;
}