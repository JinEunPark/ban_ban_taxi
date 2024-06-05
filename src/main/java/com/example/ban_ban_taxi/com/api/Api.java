package com.example.ban_ban_taxi.com.api;

import com.example.ban_ban_taxi.com.api.result.Result;
import com.example.ban_ban_taxi.com.error.ErrorCodeIfs;
import jakarta.validation.Valid;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T> {
    private Result result;

    @Valid
    private T body;

    public static<T> Api OK(T data){
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }


    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result = result;
        return api;
    }
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        return api;
    }


    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String des){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, des);
        return api;
    }
}
