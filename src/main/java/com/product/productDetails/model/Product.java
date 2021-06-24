package com.product.productDetails.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Document(collection = "product")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @org.springframework.data.annotation.Id
    private String productId;
    private String productName;
    private int quantity;
    private Double price;
}
