package com.product.productDetails.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponce {
    private String errormsg;
    private String logcode;
    private int status;
    private LocalDateTime time;
}
