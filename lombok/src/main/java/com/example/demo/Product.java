package com.example.demo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private String description;
    private int price;
}
