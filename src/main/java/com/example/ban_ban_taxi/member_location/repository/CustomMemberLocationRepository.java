package com.example.ban_ban_taxi.member_location.repository;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomMemberLocationRepository {
    public Optional<List<MemberLocation>> findAllNearMemberLocations(Geometry departLocation, Geometry desLocation, Long searchMemberId, Double radius);

}
