package com.example.ban_ban_taxi.member.repositroy.custom.impl;

import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.repositroy.custom.inter.CustomMemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
//명명 규칙 {entity명 + repository + Impl}
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final EntityManager em;

    @Override
    public void updateMember(Member member) {
        Member member1 = em.find(Member.class, member.getId());
        member1.setMember(member);
        return;
    }


    @Override
    public List<Member> findMemberInDistance(Long dis, Long id) {

        return null;
    }

}
