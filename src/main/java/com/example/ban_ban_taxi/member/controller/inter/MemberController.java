package com.example.ban_ban_taxi.member.controller.inter;

import com.example.ban_ban_taxi.com.api.Api;
import com.example.ban_ban_taxi.com.api.result.Result;
import com.example.ban_ban_taxi.member.dto.MemberIdDto;
import com.example.ban_ban_taxi.member.dto.MemberPostDto;
import com.example.ban_ban_taxi.member.dto.MemberResponseDto;
import com.example.ban_ban_taxi.member.dto.MemberUpdateDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberController {
     Api<MemberIdDto> createMember(@RequestBody MemberPostDto dto);
     Api updateMember(@RequestBody MemberUpdateDto dto);
     Api deleteMember(@RequestParam Long memberId);
     Api<MemberResponseDto> readMember(@RequestParam Long memberId);
}
