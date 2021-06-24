package com.product.productDetails.controller.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotNull(message = "productName should not be null")
    private String productName;
    @Min(value = 1, message = "Minimum quantity should be 1")
    private int quantity;
    @NotNull(message = "productPrice should not be zero")
    private Double price;

}
