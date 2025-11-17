package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringCafe implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Product product = new Product();
//        product.setName("애플와치");
//        product.setDescription("애플의 스마트와치");
//        product.setPrice(450000);

        Product product = Product.builder()
                .name("삼성갤럭시와치")
                .price(350000)
                .build();

        System.out.println("상품 = " + product);
    }
}
