package com.example.ban_ban_taxi.config.objectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper(){
        var objMapper = new ObjectMapper();
        objMapper.registerModule(new Jdk8Module()); // jdk 8 버전 이후 클래스
        objMapper.registerModule(new JavaTimeModule()); // local date
        objMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false); //모르는 json field 에 대해서는 무시한다.
        objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 날짜를 타임스탬프로 쓰지 않도록 설정 (날짜를 ISO 8601 형식으로 직렬화)
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 카멜 케이스 스내이크 케이스 공통 변환
        objMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        return objMapper;
    }
}
