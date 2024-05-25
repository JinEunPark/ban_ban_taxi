package com.example.ban_ban_taxi.geo_location.repository;

import com.example.ban_ban_taxi.geo_location.model.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationRepository extends JpaRepository<MemberLocation, Long>, CustomGeoLocationRepository{

}
