package com.dankook.authchatting.chat.room.domain;

public interface RoomRepository {
    Room save(Room room);

    Room getById(Long roomId);
}
