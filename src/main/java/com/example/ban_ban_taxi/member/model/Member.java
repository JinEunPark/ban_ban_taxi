package com.example.ban_ban_taxi.member.model;

import com.example.ban_ban_taxi.group.model.TaxiMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    @Length(min=3, max=100)
    private String password;

    @Column
    @Email(message = "올바른 email 형식이 필요합니다")
    private String email;

    @OneToOne(mappedBy = "memberId")
    private TaxiMember taxiMember;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setMember(Member m) {
        this.email = m.email;
        this.name = m.name;
        return;
    }
}
