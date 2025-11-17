package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
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

        // trace < debug < info < warn < error

        log.trace("상품 = {}", product);
        log.debug("상품 = {}", product);
        log.info("상품 = {}", product);
        log.warn("상품 = {}", product);
        log.error("상품 = {}", product);
    }

    //@Scheduled(cron = "0/5 * * * * *")
    @Scheduled(fixedDelay = 2000)
    public void dispatchAllOrdersToFullfilment() {
        log.info("물류 센터로 주문 정보 전송");
    }
}

