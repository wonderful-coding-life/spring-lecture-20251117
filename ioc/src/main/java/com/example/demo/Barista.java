package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Barista {
    private List<CoffeeMachine> coffeeMachines;

    public Barista(List<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;
    }

    public void makeCoffee() {
        for (CoffeeMachine coffeeMachine : coffeeMachines) {
            System.out.println(coffeeMachine.brew());
        }
    }
}
