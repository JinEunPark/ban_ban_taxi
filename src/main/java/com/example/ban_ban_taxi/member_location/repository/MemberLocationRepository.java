package com.example.ban_ban_taxi.member_location.repository;

import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long>, CustomMemberLocationRepository {

}
