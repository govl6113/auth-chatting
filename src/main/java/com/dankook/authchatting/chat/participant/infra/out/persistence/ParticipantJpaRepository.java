package com.dankook.authchatting.chat.participant.infra.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dankook.authchatting.chat.participant.domain.Participant;

public interface ParticipantJpaRepository extends JpaRepository<Participant, Long> {
}
