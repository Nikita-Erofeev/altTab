package com.example.altTab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:application.properties")
})
public class AltTabApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltTabApplication.class, args);
	}

}
