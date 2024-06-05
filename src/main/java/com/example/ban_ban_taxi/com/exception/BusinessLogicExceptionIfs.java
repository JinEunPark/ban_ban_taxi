package com.example.ban_ban_taxi.com.exception;

import com.example.ban_ban_taxi.com.error.ErrorCodeIfs;

public interface BusinessLogicExceptionIfs {
    public ErrorCodeIfs getErrorCodeIfs();
    public String getErrorDescription();
}
