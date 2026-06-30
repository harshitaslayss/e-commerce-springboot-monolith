package com.app.ecom_app.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String imageUrl;
}
