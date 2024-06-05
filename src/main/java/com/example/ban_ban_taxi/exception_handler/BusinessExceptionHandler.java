package com.example.ban_ban_taxi.exception_handler;

import com.example.ban_ban_taxi.com.api.Api;
import com.example.ban_ban_taxi.com.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) // 최우선 처리
@Slf4j
public class BusinessExceptionHandler {

    @ExceptionHandler(value = BusinessLogicException.class)
    public ResponseEntity<Object> businessException(
            BusinessLogicException exception
    ){
        log.error("",exception.getExceptionCode());
        var errorCode = exception.getErrorCodeIfs();
        return ResponseEntity
                .status(errorCode.getErrorCode())
                .body(
                        Api.ERROR(errorCode, exception.getErrorDescription())
                );
    }
}
