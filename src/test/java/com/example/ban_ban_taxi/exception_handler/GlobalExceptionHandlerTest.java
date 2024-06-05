package com.example.ban_ban_taxi.exception_handler;

import com.example.ban_ban_taxi.com.error.ErrorCode;
import com.example.ban_ban_taxi.com.exception.BusinessLogicException;
import com.example.ban_ban_taxi.member.controller.impl.MemberControllerImpl;
import com.example.ban_ban_taxi.member.dto.MemberResponseDto;
import com.example.ban_ban_taxi.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)//필터 제외하고 test
class GlobalExceptionHandlerTest {


    @MockBean
    private MemberServiceImpl memberService;
    private MockMvc mockMvc;

    private final String URI = "/api/member";

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new MemberControllerImpl(memberService))
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }


    @Test
    void businessException() throws Exception {
        Long readMemberId = 1L;

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .email("wlsdms@")
                .id(1L)
                .name("wlsdms")
                .build();

        when(memberService.retrieveMemberById(any(Long.class)))
                .thenThrow(new BusinessLogicException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(get(URI + "/read")
                        .param("memberId", readMemberId.toString()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

    }
}