package com.product.productDetails.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InternalStandardError {

    PRODUCT_NOT_EXIST("Product Not Exist","E0001)",HttpStatus.NOT_FOUND),
    INVALID_INPUT("Invalid Input","E0002",HttpStatus.BAD_REQUEST);

    private String errorMsg;
    private String logCode;
    private HttpStatus statusCode;
}
