package com.example.ban_ban_taxi.member.repositroy;

import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.repositroy.custom.inter.CustomMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> , CustomMemberRepository {
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByEmail(String email);
}
