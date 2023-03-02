package com.gk.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class OrderResponseDTO {
    private Long orderId;
    private Long productId;
    private String productName;
    private Float price;
    private LocalDate orderDate;
    private Long userId;
    private String userName;
}
