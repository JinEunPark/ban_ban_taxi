package com.example.ban_ban_taxi.member.dto;

import com.example.ban_ban_taxi.member.model.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberIdDto{
    Long id;

    public static MemberIdDto to(Member m){
        return MemberIdDto.builder()
                .id(m.getId())
                .build();
    }
}
