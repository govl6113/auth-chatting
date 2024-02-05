package com.dankook.authchatting.chat.room.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankook.authchatting.chat.participant.domain.Participant;
import com.dankook.authchatting.chat.participant.domain.ParticipantRepository;
import com.dankook.authchatting.chat.room.domain.Room;
import com.dankook.authchatting.chat.room.domain.RoomRepository;
import com.dankook.authchatting.chat.room.infra.in.web.request.RoomCreateRequest;
import com.dankook.authchatting.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ParticipantRepository participantRepository;
    private final MemberRepository memberRepository;

    @Override
    public Room create(Long memberId, RoomCreateRequest request) {
        Room room = roomRepository.save(Room.builder().build());

        participantRepository.save(Participant.builder()
                                              .room(room)
                                              .member(memberRepository.getById(memberId))
                                              .build());

        if (request.getMemberIds() != null) {
            request.getMemberIds().forEach(it -> {
                participantRepository.save(Participant.builder()
                                                      .room(room)
                                                      .member(memberRepository.getById(it))
                                                      .build());
            });
        }

        return room;
    }

    @Override
    public Room enter(Long roomId, Long memberId) {
        Room room = roomRepository.getById(roomId);

        participantRepository.save(Participant.builder()
                                              .room(room)
                                              .member(memberRepository.getById(memberId))
                                              .build());

        return room;
    }
}
