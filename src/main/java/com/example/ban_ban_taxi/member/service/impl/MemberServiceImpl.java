package com.example.ban_ban_taxi.member.service.impl;

import com.example.ban_ban_taxi.com.error.ErrorCode;
import com.example.ban_ban_taxi.com.error.ErrorCodeIfs;
import com.example.ban_ban_taxi.com.exception.BusinessLogicException;
import com.example.ban_ban_taxi.member.dto.*;
import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.repositroy.MemberRepository;
import com.example.ban_ban_taxi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public MemberIdDto saveMember(MemberPostDto postDto) {

        Optional<Member> memberOpt = memberRepository.findMemberByEmail(postDto.getEmail());

        if(memberOpt.isPresent()) throw new BusinessLogicException(ErrorCode.DUPLICATION_EMAIL_ERROR);

        Member member = postDto.of();
        Member saved = memberRepository.save(member);

        return MemberIdDto.to(saved);
    }

    @Override
    public MemberResponseDto retrieveMemberById(Long id) {

        Optional<Member> result = memberRepository.findMemberById(id);
        Member response = result
                .orElseThrow(()-> new BusinessLogicException(ErrorCode.USER_NOT_FOUND));

        return MemberResponseDto.to(response);
    }

    @Override
    @Transactional
    public void updateMember(MemberUpdateDto updateDto) {
        Optional<Member> result = memberRepository.findMemberById(updateDto.getId());
        Member member = result
                .orElseThrow(()-> new BusinessLogicException(ErrorCode.USER_NOT_FOUND));

        member.setMember(updateDto.to());
        memberRepository.updateMember(member);
        return;
    }

    @Override
    @Transactional
    public void deleteMemberById(Long id) {
        Optional<Member> result = memberRepository.findMemberById(id);

        Member member = result
                .orElseThrow(()-> new BusinessLogicException(ErrorCode.USER_NOT_FOUND,"생성된 유저가 없어 삭제를 실행할 수 없습니다"));

        memberRepository.deleteById(id);
        return;
    }
}
