package com.example.ban_ban_taxi.com.api.result;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Result {
    private  Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(200)
                .resultMessage("OK")
                .resultDescription("성공")
                .build();
    }
}
