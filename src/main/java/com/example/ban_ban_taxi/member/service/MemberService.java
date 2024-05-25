package com.example.ban_ban_taxi.member.repositroy;

import com.example.ban_ban_taxi.member.model.Member;
import org.aspectj.weaver.AjcMemberMaker;

public interface MemberService {
    Long saveMember(Member member);
    Member retrieveMemberById(Long id);
    int updateMember(Member member);
    void deleteMemberById(Long id);
}
