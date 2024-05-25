package com.example.ban_ban_taxi.member.repositroy.custom.inter;

import com.example.ban_ban_taxi.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomMemberRepository {
    void updateMember(Member member);
    List<Member> findMemberInDistance(Long dis, Long id);

}
