package com.example.ban_ban_taxi.member.controller.impl;

import com.example.ban_ban_taxi.com.api.Api;
import com.example.ban_ban_taxi.member.controller.inter.MemberController;
import com.example.ban_ban_taxi.member.dto.MemberIdDto;
import com.example.ban_ban_taxi.member.dto.MemberPostDto;
import com.example.ban_ban_taxi.member.dto.MemberResponseDto;
import com.example.ban_ban_taxi.member.dto.MemberUpdateDto;
import com.example.ban_ban_taxi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {
    private final MemberService memberService;
    //추후 이메일 검증 로직 추가할 것
    @Override
    @PostMapping
    public Api<MemberIdDto> createMember(@RequestBody MemberPostDto dto) {
        Api<MemberIdDto> response = Api.OK(memberService.saveMember(dto));
        return response;
    }

    @Override
    @PatchMapping
    public Api updateMember(@RequestBody MemberUpdateDto dto) {
        memberService.updateMember(dto);
        Api.OK("update complete");
        return null;
    }

    @Override
    @DeleteMapping("/delete")
    public Api deleteMember(@RequestParam Long memberId) {
        memberService.deleteMemberById(memberId);
        return Api.OK("delete complete");
    }

    @Override
    @GetMapping("/read")
    public Api<MemberResponseDto> readMember(@RequestParam Long memberId) {
        MemberResponseDto responseDto = memberService.retrieveMemberById(memberId);
        return Api.OK(responseDto);
    }
}
