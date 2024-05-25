package com.example.ban_ban_taxi.member.dto;


import com.example.ban_ban_taxi.member.model.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto{
    Long id;
    String name;
    String email;

    public static MemberResponseDto to(Member m){
        return MemberResponseDto.builder()
                .name(m.getName())
                .email(m.getEmail())
                .id(m.getId())
                .build();
    }
}
