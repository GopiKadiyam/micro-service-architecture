package com.gk.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long orderId;
    private String productName;
    private Float price;
}
