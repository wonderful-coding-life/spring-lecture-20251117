package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringCafe {
    @Autowired
    private Barista barista;

    @PostConstruct
    public void run() {
        barista.makeCoffee();
    }
}
