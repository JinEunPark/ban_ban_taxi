package com.example.ban_ban_taxi.exception_handler;

import com.example.ban_ban_taxi.com.api.Api;
import com.example.ban_ban_taxi.com.error.ErrorCode;
import com.example.ban_ban_taxi.com.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) //가장 마지막에서 적용되어서 동작하도록

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
        log.error("", exception);// 로그로는 발생한 오류를 남기지만

        return ResponseEntity
                .status(500)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)//클라이언트 사이드에서는 서버에서 발생한 에러가 무엇인지 알 수 없음
                );

    }
}
