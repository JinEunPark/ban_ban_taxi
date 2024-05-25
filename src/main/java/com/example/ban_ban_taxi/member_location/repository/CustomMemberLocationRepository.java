package com.example.ban_ban_taxi.geo_location.repository;

import com.example.ban_ban_taxi.geo_location.model.MemberLocation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomGeoLocationRepository {
    Optional<List<MemberLocation>> findAllNearMemberLocations(MemberLocation memberLocation, Double radius);
}
