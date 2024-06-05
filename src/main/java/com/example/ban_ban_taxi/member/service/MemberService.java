package com.example.ban_ban_taxi.member.service;

import com.example.ban_ban_taxi.member.dto.*;
import com.example.ban_ban_taxi.member.model.Member;
import org.aspectj.weaver.AjcMemberMaker;
import org.springframework.stereotype.Service;

public interface MemberService {
    MemberIdDto saveMember(MemberPostDto postDto);
    MemberResponseDto retrieveMemberById(Long id);
    void updateMember(MemberUpdateDto member);
    void deleteMemberById(Long id);
}
