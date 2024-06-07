package com.example.ban_ban_taxi.car_pool;

import com.example.ban_ban_taxi.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn
    @OneToOne
    private Member memberId;

    @Column
    private String model;

    @Column(name = "max_passenger")
    private Integer maxPassenger;//차량 최대 탑승자

    @Column(name = "car_identifier")
    private String carIdentifier; // 차량 번호
}
