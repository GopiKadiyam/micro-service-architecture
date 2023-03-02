package com.gk.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    private Long orderId;
    private Long productId;
    private Long userId;
    private LocalDate orderDate;

    public Long test() {
        return orderId;
    }

    public void testp(Long orderId) {
        System.out.println("hjhjb");
    }
}
