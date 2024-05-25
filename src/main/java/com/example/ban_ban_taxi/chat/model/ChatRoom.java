package com.example.ban_ban_taxi.chat.model;

import com.example.ban_ban_taxi.group.model.TaxiGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn
    TaxiGroup taxiGroupId;

    @OneToMany(mappedBy = "chatRoomId")
    List<ChatMessage> chatMessageList = new ArrayList<>();

    @CreationTimestamp
    LocalDateTime createdAt;
}
