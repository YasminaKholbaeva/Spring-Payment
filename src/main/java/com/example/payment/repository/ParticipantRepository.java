package com.example.payment.repository;

import com.example.payment.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByEventId(Integer eventId);

    Optional<Participant> findByEventIdAndParticipantId(Integer eventId, Integer participantId);

    void deleteByEventIdAndParticipantId(Integer eventId, Integer participantId);
}
