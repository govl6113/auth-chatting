package com.dankook.authchatting.chat.room.infra.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dankook.authchatting.chat.room.application.RoomService;
import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.chat.room.infra.in.web.request.RoomCreateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/new")
    public ResponseEntity<Room> create(Authentication authentication, RoomCreateRequest request) {
        return ResponseEntity.ok(roomService.create(Long.parseLong(authentication.getName()), request));
    }
}
