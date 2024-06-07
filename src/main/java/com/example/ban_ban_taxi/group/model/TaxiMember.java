package com.example.ban_ban_taxi.group.model;

import com.example.ban_ban_taxi.member.model.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TaxiMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="taxi_group_id",nullable = false)
    private TaxiGroup taxiGroupId; //연관관계 주인

    @OneToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member memberId; //연관관계 주인
}
