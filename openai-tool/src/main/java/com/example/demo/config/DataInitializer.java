package com.example.demo.config;

import com.example.demo.model.ProductOrder;
import com.example.demo.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private ProductOrderRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(ProductOrder.builder()
                        .orderNumber("P1000")
                        .productName("맥북에어")
                        .address("서울시 영등포구 여의도동")
                        .status("배송중").build());
        repository.save(ProductOrder.builder()
                .orderNumber("P1001")
                .productName("아이폰")
                .address("서울시 강남구 역삼동")
                .status("상품 준비중").build());
    }
}
