package com.example.ban_ban_taxi.member.service.impl;

import com.example.ban_ban_taxi.member.dto.*;
import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.repositroy.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("save_member_test")
    void save_member_test() {

        Member savedMember = createMember();
        when(memberRepository.save(Mockito.any(Member.class))).thenReturn(savedMember);
        //mock 객쳉의 동작 정의 어떤 member 객체를 저장했을 때 위에서 생성된 member 를 반혼한다고 가정함.
        MemberPostDto dto = MemberPostDto.builder()
                .email("wlsdms@email")
                .name("wlsdms")
                .build();
        //given

        MemberIdDto response = memberService.saveMember(dto);
        //when

        Assertions.assertNotNull(response);
        //then
    }

    @Test
    void save_member_test_same_email() {
        MemberPostDto dto = MemberPostDto.builder()
                .email("wlsdms@email")
                .name("wlsdms")
                .build();
        //given

        Member savedMember = createMember();
//        when(memberRepository.save(Mockito.any(Member.class)))
//                .thenReturn(savedMember)
//              위의 저장 메소드는 아래의 조회 메소드에서 오류를 던지며 끝나기 때문에 필요없다.
        when(memberRepository.findMemberByEmail(Mockito.any(String.class)))
                .thenReturn(Optional.of(savedMember));
        //when

        Assertions.assertThrows(RuntimeException.class, ()-> memberService.saveMember(dto));
        //then
    }


    @Test
    void retrieveMemberById() {
        Long fakeId = 1L;
        Member retrievedMember = createMember();
        when(memberRepository.findMemberById(fakeId)).thenReturn(Optional.of(retrievedMember));
        //given
        MemberResponseDto dto = memberService.retrieveMemberById(fakeId);
        //when
        Assertions.assertNotNull(dto);
        //then
    }

    @Test
    void updateMember() {
        MemberUpdateDto updateDto = MemberUpdateDto.builder()
                .id(1L)
                .email("updated@gmail.com")
                .name("update")
                .build();

        Member updatedMember = Member.builder()
                .id(1L)
                .email("updated@gmail.com")
                .name("update")
                .build();
        //given
        when(memberRepository.findMemberById(updatedMember.getId()))
                .thenReturn(Optional.of(createMember()));
        memberService.updateMember(updateDto);
        //when

        verify(memberRepository).updateMember(Mockito.any(Member.class));
        //then 행위 검증 호츌 여부
    }


    @Test
    void updateMember_wrong_member_id() {
        MemberUpdateDto updateDto = MemberUpdateDto.builder()
                .id(12341234L)
                .email("updated@gmail.com")
                .name("update")
                .build();

        //given
        when(memberRepository.findMemberById(updateDto.getId()))
                .thenReturn(Optional.ofNullable(null));

        //when

        Assertions.assertThrows(RuntimeException.class,
                ()-> memberService.updateMember(updateDto));
        //then 행위 검증 호츌 여부
    }

    @Test
    void deleteMemberById() {
        Long fakeId = 1L;
        //given
        when(memberRepository.findMemberById(fakeId))
                .thenReturn(Optional.of(createMember()));
        //when
        memberService.deleteMemberById(fakeId);

        //then
        verify(memberRepository).deleteById(fakeId); //행위 검증
    }


    private Member createMember() {
        return Member.builder()
                .id(1L)
                .name("wlsdms")
                .email("wlsdmsrnfl@email")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}