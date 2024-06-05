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

    String password;
    public Member to(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }

    public Member of(){
        return Member.builder()
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .build();
    }

}
