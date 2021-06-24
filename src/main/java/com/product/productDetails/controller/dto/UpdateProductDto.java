package com.product.productDetails.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProductDto {
    private String productName;
    private int quantity;
    private Double price;

}
