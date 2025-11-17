package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringCafe implements ApplicationRunner {
    @Autowired
    private Barista barista;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        barista.makeCoffee();
        List<String> names = args.getOptionValues("name");
        if (names != null) {
            for (String name : names) {
                System.out.println("name = " + name);
            }
        }
    }
}
