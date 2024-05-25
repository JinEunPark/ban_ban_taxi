package com.example.ban_ban_taxi.member.repositroy;

import com.example.ban_ban_taxi.member.model.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    private Member getSavedMember(){
        return memberRepository.findMemberById(1L).get();
    }

    @BeforeEach
     void setUp() {
        if(memberRepository.findById(1L).isPresent()) return;
        Member member = Member.builder()
                .email("wlsdmsrnfl@naver.com")
                .name("박진은")
                .build();
       memberRepository.save(member);
    }

    @Test
    @DisplayName("업데이트 맴버")
    void custom_update_member_test(){
        Member member = getSavedMember();
        Member updated = Member.builder()
                .email("변경됨@naver.com")
                .name("박진은")
                .build();
        member.setMember(updated);
        memberRepository.updateMember(member);
        Member done = memberRepository.findById(member.getId())
                .orElseThrow(()-> new RuntimeException("there is no member"));
        Assertions.assertEquals("변경됨@naver.com",done.getEmail());
    }
}