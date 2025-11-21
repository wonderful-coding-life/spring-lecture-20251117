package com.example.demo.tool;

import com.example.demo.repository.ProductOrderRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderTools {
    @Autowired
    private ProductOrderRepository repository;

    @Tool(description = "상품 주문 목록을 알려 줍니다.")
    public String getProductOrders() {
        String result = "주문 목록은 다음과 같아요.\n";
        var productOrders = repository.findAll();
        for (var productOrder : productOrders) {
            result += "주문번호=" + productOrder.getOrderNumber();
            result += ", 상품이름=" + productOrder.getProductName();
            result += ", 배송주소=" + productOrder.getAddress();
            result += ", 배송상태=" + productOrder.getStatus() + "\n";
        }
        return result;
    }

    @Tool(description = "주문을 취소한다.")
    public String cancelOrder(@ToolParam(description = "주문번호") String orderNumber) {
        var order = repository.findByOrderNumber(orderNumber);
        if (order.isPresent()) {
            if ("배송중".equals(order.get().getStatus())) {
                return "배송중인 상품은 취소할 수 없습니다.";
            } else {
                repository.delete(order.get());
                return "주문이 취소되었습니다.";
            }
        } else {
            return "없는 주문 번호입니다.";
        }
    }
}
