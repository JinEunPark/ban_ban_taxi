package com.example.ban_ban_taxi.member_location.model;

import com.example.ban_ban_taxi.member.model.Member;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MemberLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geoId;

    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "depart_location",
            nullable = false)
    private Point departLocation;

    @Column(columnDefinition = "Geometry(Point,3857)",
            name = "des_location",
            nullable = false)
    private Point desLocation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;
}
