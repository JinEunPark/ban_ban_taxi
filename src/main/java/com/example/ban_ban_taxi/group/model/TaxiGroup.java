package com.example.ban_ban_taxi.group.model;

import com.example.ban_ban_taxi.chat.model.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class TaxiGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depart_add",
            nullable = false)
    private String departAdd;

    @Column(name = "des_add",
            nullable = false)
    private String desAdd;

    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "depart_location",
            nullable = false)
    private Point departureLocation;

    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "des_location",
            nullable = false)
    private Point destinationLocation;

    @Column(name = "people_num")
    private Integer peopleNum;

    @Column(name ="departure_time")
    private LocalDateTime departureTime;

    @Column(name ="arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name="taxi_fee")//추정치
    private BigDecimal fee;

    @OneToOne(mappedBy = "taxiGroupId")
    private ChatRoom chatRoomId;

    @OneToMany(mappedBy = "taxiGroupId", cascade = CascadeType.REMOVE)
    private List<TaxiMember> taxiMembers = new ArrayList<>();

}
