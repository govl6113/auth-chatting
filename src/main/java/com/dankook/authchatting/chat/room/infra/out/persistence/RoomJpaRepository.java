package com.dankook.authchatting.chat.room.infra.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.chat.room.domain.Room;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
