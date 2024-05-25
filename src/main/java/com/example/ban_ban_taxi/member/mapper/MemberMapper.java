package com.example.ban_ban_taxi.member.mapper;

import com.example.ban_ban_taxi.member.dto.MemberDto;
import com.example.ban_ban_taxi.member.dto.MemberPostDto;
import com.example.ban_ban_taxi.member.model.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {
    Member postDtoToMember(MemberPostDto postDto);
}
