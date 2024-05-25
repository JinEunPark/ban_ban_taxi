package com.example.ban_ban_taxi.group.model;

import com.example.ban_ban_taxi.chat.model.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "people_num")
    private Integer peopleNum;

    @OneToOne(mappedBy = "taxiGroupId")
    private ChatRoom chatRoomId;

    @OneToMany(mappedBy = "taxiGroupId", cascade = CascadeType.REMOVE)
    private List<TaxiMember> taxiMembers = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime departureTime;
}
