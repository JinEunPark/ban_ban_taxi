package com.example.ban_ban_taxi.member.repositroy;

import com.example.ban_ban_taxi.com.error.ErrorCode;
import com.example.ban_ban_taxi.com.exception.BusinessLogicException;
import com.example.ban_ban_taxi.member.model.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

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
    void read_member_test(){
        //given
        Member member = Member.builder()
                .email(UUID.randomUUID() + "@gmail.com").build();
        Member created = memberRepository.save(member);

        //when
        Optional<Member> memberOptional = memberRepository.findMemberById(created.getId());

        //then
        Assertions.assertTrue(memberOptional.isPresent());

    }
    @Test
    void save_member_test(){
        //given
        Member member = Member.builder()
                .email(UUID.randomUUID() + "@gmail.com").build();
        //when
        Member created = memberRepository.save(member);
        //then
        Assertions.assertNotNull(created);
    }


    @Test
    void delete_member_test(){
        //given
        Member member = Member.builder()
                .email(UUID.randomUUID() + "@gmail.com").build();
        //when
        Member created = memberRepository.save(member);
        memberRepository.deleteById(created.getId());

        //then
        Assertions.assertFalse(memberRepository.findMemberById(created.getId()).isPresent());
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