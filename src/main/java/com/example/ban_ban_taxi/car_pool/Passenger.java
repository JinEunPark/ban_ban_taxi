package com.example.ban_ban_taxi.car_pool;

import com.example.ban_ban_taxi.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Passenger {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "passenger_id")
    private Member passengerId;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contractId;

}
