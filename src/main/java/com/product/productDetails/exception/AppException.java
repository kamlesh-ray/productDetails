package com.product.productDetails.exception;

import lombok.Getter;
@Getter
public class AppException extends RuntimeException {

    private InternalStandardError errorEnum;

    public AppException(InternalStandardError errorEnum){
        super(errorEnum.getErrorMsg());
        this.errorEnum=errorEnum;
    }
}
