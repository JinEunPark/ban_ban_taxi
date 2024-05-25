package com.example.ban_ban_taxi.geo_location.repository.impl;

import com.example.ban_ban_taxi.geo_location.model.MemberLocation;
import com.example.ban_ban_taxi.geo_location.repository.CustomGeoLocationRepository;

import java.util.List;
import java.util.Optional;

public class CustomGeoLocationRepositoryImpl implements CustomGeoLocationRepository {

    @Override
    public Optional<List<MemberLocation>> findAllNearMemberLocations(MemberLocation memberLocation, Double radius) {

        return null;
    }


}
