package com.example.ban_ban_taxi.member.dto;

import com.example.ban_ban_taxi.member.model.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateDto {
    private Long id;
    private String email;
    private String name;

    private String password;

    public Member to(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
