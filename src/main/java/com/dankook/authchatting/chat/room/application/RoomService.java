package com.dankook.authchatting.chat.room.application;

import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.chat.room.infra.in.web.request.RoomCreateRequest;

public interface RoomService {
    Room create(Long memberId, RoomCreateRequest request);

    Room enter(Long roomId, Long memberId);
}
