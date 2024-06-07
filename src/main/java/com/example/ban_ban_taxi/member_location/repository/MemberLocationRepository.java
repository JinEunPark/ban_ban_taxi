package com.example.ban_ban_taxi.member_location.repository;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long>, CustomMemberLocationRepository {

    @Query(value =
            "SELECT ml.geo_id, ml.depart_location, ml.des_location, ml.member_id " +
                    "FROM member_location ml " +
                    "JOIN member m ON m.id = ml.member_id " +
                    "WHERE ST_DWithin(ml.des_location, :desLocation, :radius) " +
                    "AND ST_DWithin(ml.depart_location, :departLocation, :radius) " +
                    "AND m.id != :searchMemberId " +
                    "ORDER BY ST_Distance(ml.depart_location, :departLocation), ST_Distance(ml.des_location, :desLocation)",
            nativeQuery = true)
    Optional<List<MemberLocation>> findAllNearMemberLocations(
            @Param("departLocation") Geometry departLocation,
            @Param("desLocation") Geometry desLocation,
            @Param("searchMemberId") Long memberId,
            @Param("radius") Double radius
    );

}
