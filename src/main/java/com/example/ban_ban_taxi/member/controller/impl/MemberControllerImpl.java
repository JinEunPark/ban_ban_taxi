package com.example.ban_ban_taxi.member.controller.impl;

import com.example.ban_ban_taxi.com.api.Api;
import com.example.ban_ban_taxi.member.controller.inter.MemberController;
import com.example.ban_ban_taxi.member.dto.MemberIdDto;
import com.example.ban_ban_taxi.member.dto.MemberPostDto;
import com.example.ban_ban_taxi.member.dto.MemberResponseDto;
import com.example.ban_ban_taxi.member.dto.MemberUpdateDto;
import com.example.ban_ban_taxi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {
    private final MemberService memberService;
    //추후 이메일 검증 로직 추가할 것
    @Override
    public Api<MemberIdDto> createMember(MemberPostDto dto) {
        Api<MemberIdDto> response = Api.OK(memberService.saveMember(dto));
        return response;
    }

    @Override
    public Api updateMember(MemberUpdateDto dto) {
        memberService.updateMember(dto);
        Api.OK("update complete");
        return null;
    }

    @Override
    public Api deleteMember(Long memberId) {
        return null;
    }

    @Override
    public Api<MemberResponseDto> readMember(Long memberId) {
        return null;
    }
}
