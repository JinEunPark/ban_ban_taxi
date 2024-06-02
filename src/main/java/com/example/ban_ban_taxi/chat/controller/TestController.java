package com.example.ban_ban_taxi.chat.controller;

import com.example.ban_ban_taxi.com.api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public Api getTest(){
        return Api.OK("this is test , api connection find don't worry");
    }
}
