package com.product.productDetails.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponce> handleAppException(AppException exception) {
        log.error("error occurred {}", exception.getMessage());
        InternalStandardError internalStandardError = exception.getErrorEnum();

        ErrorResponce resp = ErrorResponce.builder()
                .status(internalStandardError.getStatusCode().value())
                .logcode(internalStandardError.getLogCode())
                .time(LocalDateTime.now())
                .errormsg(internalStandardError.getErrorMsg())
                .build();
        return ResponseEntity.status(internalStandardError.getStatusCode()).body(resp);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponce> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().iterator().next().getDefaultMessage();
        log.error("error occurred {}", errorMessage);
        InternalStandardError invalidInputError = InternalStandardError.INVALID_INPUT;
        ErrorResponce resp = ErrorResponce.builder()
                .status(invalidInputError.getStatusCode().value())
                .logcode("E0002")
                .time(LocalDateTime.now())
                .errormsg(invalidInputError.getErrorMsg() + errorMessage)
                .build();
        return ResponseEntity.status(invalidInputError.getStatusCode()).body(resp);
    }
}
