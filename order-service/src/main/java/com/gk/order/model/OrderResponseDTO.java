package com.gk.order.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class OrderResponseDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Long productId;
    private String productName;
    private Float price;
    private Long userId;
    private String userName;
}
