package com.example.altTab.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("classpath:application.properties")
public class TgBotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;
}