package com.dankook.authchatting.chat.participant.infra.out.persistence;

import org.springframework.stereotype.Repository;

import com.dankook.authchatting.chat.participant.domain.Participant;
import com.dankook.authchatting.chat.participant.domain.ParticipantRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private final ParticipantJpaRepository participantRepository;

    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }
}
