package com.example.ban_ban_taxi.member.service.impl;

import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberRepositoryImpl implements MemberService {

    @Override
    public Long saveMember(Member member) {
        return null;
    }

    @Override
    public Member retrieveMemberById(Long id) {
        return null;
    }


    @Override
    public int updateMember(Member member) {
        return 0;
    }

    @Override
    public void deleteMemberById(Long id) {

    }
}
