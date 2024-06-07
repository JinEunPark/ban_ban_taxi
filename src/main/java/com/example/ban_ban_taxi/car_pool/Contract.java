package com.example.ban_ban_taxi.car_pool;

import com.example.ban_ban_taxi.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "driver_id")
    @OneToOne
    private Member driverId;// 돈 받는 운전자

    @OneToMany
    private List<Passenger> passengers; // 계약자 아이디
    
    @Column(name = "departure_address")
    private String departAdd;//출발 주소
    
    @Column(name = "destination_address")
    private String desAdd;//도착 주소
    
    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "departure_location",
            nullable = false)
    private Point departurePoint; // 출발 주소 point v
    
    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "des_location",
            nullable = false)
    private Point destinationPoint; // 도착 주소 point v

    @Column(name = "departure_time")
    private LocalDate departureTime;

    @Column(name = "arrival_time")
    private LocalDate arrivalTime;

    @Column(name = "contract_date")
    private LocalDate contractDate; //계약 시작일

    @Column(name = "contract_complete_date")
    private LocalDate contractCompleteDate; // 계약 만료일
}
