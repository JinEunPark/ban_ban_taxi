package com.example.ban_ban_taxi.member_location.repository.impl;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import com.example.ban_ban_taxi.member_location.repository.CustomMemberLocationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CustomMemberLocationRepositoryImpl implements CustomMemberLocationRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Optional<List<MemberLocation>> findAllNearMemberLocations(
            Geometry departLocation,
            Geometry desLocation,
            Long searchMemberId,
            Double radius) {

        String sql =
                "SELECT ml.geo_id, ml.depart_location, ml.des_location, ml.member_id " +
                        "FROM member_location ml " +
                        "JOIN member m ON m.id = ml.member_id " +
                        "WHERE ST_DWithin(ml.des_location, :desLocation, :radius) " +
                        "AND ST_DWithin(ml.depart_location, :departLocation, :radius) " +
                        "AND m.id != :searchMemberId " +
                        "ORDER BY ST_Distance(ml.depart_location, :departLocation), ST_Distance(ml.des_location, :desLocation)";

        Query query = em.createNativeQuery(sql, MemberLocation.class);
        query.setParameter("departLocation", departLocation);
        query.setParameter("desLocation", desLocation);
        query.setParameter("searchMemberId", searchMemberId);
        query.setParameter("radius", radius);

        List<MemberLocation> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }
}
