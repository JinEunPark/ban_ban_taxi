package com.example.ban_ban_taxi.com.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter

public enum ErrorCode implements ErrorCodeIfs{
    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512,"NULL POINT EXCEPTION"),


    // user exception 1000 ~~~ 1999 user domain exception
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 400, "해당 유저를 찾을 수 없습니다" ),
    DUPLICATION_EMAIL_ERROR(HttpStatus.BAD_REQUEST.value(), 413, "중복된 이메일 사용은 허용되지 않습니다"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
