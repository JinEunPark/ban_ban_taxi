package com.example.ban_ban_taxi.chat.model;

import com.example.ban_ban_taxi.group.model.TaxiGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "sender_id")
    TaxiGroup senderId;

    @Column
    String content;

    @ManyToOne
    @JoinColumn(name = "char_room_id")
    ChatRoom chatRoomId;

    @CreationTimestamp
    LocalDateTime createdAt;
}
