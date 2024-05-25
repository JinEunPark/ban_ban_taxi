package com.example.ban_ban_taxi.member.dto;

import com.example.ban_ban_taxi.member.model.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPostDto {
    String email;
    String name;
    public Member to(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }

    public Member of(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }

}
