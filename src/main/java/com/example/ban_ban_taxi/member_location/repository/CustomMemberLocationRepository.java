package com.example.ban_ban_taxi.member_location.repository;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomMemberLocationRepository {
    Optional<List<MemberLocation>> findAllNearMemberLocations(MemberLocation memberLocation, Double radius);
}
