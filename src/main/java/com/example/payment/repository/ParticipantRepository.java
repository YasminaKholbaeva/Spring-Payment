package com.example.payment.repository;

import com.example.payment.model.Participant;
import com.example.payment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByEventId(Integer eventId);

    List<Participant> findByUser(User user);

    Optional<Participant> findByIdAndEventId(Integer userId, Integer eventId);

    void deleteByIdAndEventId(Integer userId, Integer eventId);


}
