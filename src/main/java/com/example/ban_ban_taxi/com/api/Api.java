package com.example.ban_ban_taxi.com.api;

import com.example.ban_ban_taxi.com.api.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
