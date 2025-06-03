package com.dankook.authchatting.chat.participant.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.member.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public Participant(Member member, Room room) {
        this.member = member;
        this.room = room;
    }
}
