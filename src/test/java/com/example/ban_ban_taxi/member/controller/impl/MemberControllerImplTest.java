package com.example.ban_ban_taxi.member.controller.impl;

import com.example.ban_ban_taxi.member.dto.MemberIdDto;
import com.example.ban_ban_taxi.member.dto.MemberPostDto;
import com.example.ban_ban_taxi.member.dto.MemberResponseDto;
import com.example.ban_ban_taxi.member.dto.MemberUpdateDto;
import com.example.ban_ban_taxi.member.service.MemberService;
import com.example.ban_ban_taxi.member.service.impl.MemberServiceImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.geolatte.geom.M;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MemberControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)//필터 제외하고 test
@ExtendWith(MockitoExtension.class)

class MemberControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberServiceImpl memberService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URI = "/api/member";

    @Test
    void createMember() throws Exception {
        // given
        MemberPostDto memberPostDto = MemberPostDto.builder()
                .email(UUID.randomUUID() + "@gmail.com")
                .name("wlsdsm")
                .password(UUID.randomUUID().toString())
                .build();

        MemberIdDto memberIdDto = new MemberIdDto(1L);

        when(memberService.saveMember(any(MemberPostDto.class))).thenReturn(memberIdDto);

        // when & then
        mockMvc.perform(
                        post(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(memberPostDto))
                                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andReturn();

        verify(memberService).saveMember(any(MemberPostDto.class));
    }

    @Test
    void updateMember() throws Exception {
        //given
        MemberUpdateDto memberUpdateDto = MemberUpdateDto.builder()
                .email(UUID.randomUUID() + "@gmail.com")
                .name("wlsdsm")
                .password("newPassword")
                .build();


        //when
        mockMvc.perform(
                        patch(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(memberUpdateDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(memberService).updateMember(any(MemberUpdateDto.class));
        // Test case for updateMember
    }

    @Test
    void deleteMember() throws Exception {
        //given
        Long deleteId = 1L;

        //when & then
        mockMvc.perform(
                        delete(URI + "/delete")
                                .param("memberId",deleteId.toString()))
                .andExpect(status().isOk());

        verify(memberService).deleteMemberById(deleteId);
    }

    @Test
    void readMember() throws Exception{
        Long readMemberId = 1L;

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .email("wlsdms@")
                .id(1L)
                .name("wlsdms")
                .build();

        when(memberService.retrieveMemberById(any(Long.class))).thenReturn(memberResponseDto);

        MvcResult res = mockMvc.perform(get(URI + "/read")
                        .param("memberId", readMemberId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body").exists())
                .andExpect(jsonPath("$..email").exists())
                .andReturn();
        // Test case for readMember
    }
}
