package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CafeConfig {
    @Bean
    public CoffeeMachine mochaCoffeeMachine() {
        return () -> "Brewing coffee with Mocha Coffee Machine";
    }

    @Bean
    public CoffeeMachine latteCoffeeMachine() {
        return () -> "Brewing coffee with Latte Coffee Machine";
    }
}
