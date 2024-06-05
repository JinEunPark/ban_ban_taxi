package com.example.ban_ban_taxi.com.exception;

import com.example.ban_ban_taxi.com.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

//커스텀 Exception

@Getter
public class BusinessLogicException extends RuntimeException implements BusinessLogicExceptionIfs, Serializable{

    private final ErrorCodeIfs errorCodeIfs; // 커스텀 예외 코드번호
    private final String errorDescription;

    public BusinessLogicException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription());
        this.errorDescription = errorCodeIfs.getDescription();
        this.errorCodeIfs = errorCodeIfs;
    }

    public BusinessLogicException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorCodeIfs.getDescription());
        this.errorDescription = errorDescription;
        this.errorCodeIfs = errorCodeIfs;
    }


    public BusinessLogicException(ErrorCodeIfs errorCodeIfs, Throwable tx){
        super(tx);
        this.errorDescription = errorCodeIfs.getDescription();
        this.errorCodeIfs = errorCodeIfs;
    }

    public BusinessLogicException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorDescription){
        super(tx);
        this.errorDescription = errorDescription;
        this.errorCodeIfs = errorCodeIfs;
    }


    public ErrorCodeIfs getExceptionCode() {
        return errorCodeIfs;
    }
}

