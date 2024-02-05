package com.dankook.authchatting.chat.room.infra.out.persistence;

import org.springframework.stereotype.Repository;

import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.chat.room.domain.RoomRepository;
import com.dankook.authchatting.chat.room.exception.NotFoundRoomException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomJpaRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(NotFoundRoomException::new);
    }
}
