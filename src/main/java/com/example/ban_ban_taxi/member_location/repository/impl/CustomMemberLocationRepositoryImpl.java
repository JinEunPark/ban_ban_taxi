package com.example.ban_ban_taxi.member_location.repository.impl;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import com.example.ban_ban_taxi.member_location.repository.CustomMemberLocationRepository;

import java.util.List;
import java.util.Optional;

public class CustomMemberLocationRepositoryImpl implements CustomMemberLocationRepository {

    @Override
    public Optional<List<MemberLocation>> findAllNearMemberLocations(MemberLocation memberLocation, Double radius) {

        return null;
    }


}
