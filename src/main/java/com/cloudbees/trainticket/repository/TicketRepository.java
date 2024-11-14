package com.cloudbees.trainticket.repository;

import com.cloudbees.trainticket.model.Ticket;
import com.cloudbees.trainticket.model.TrainSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySection(TrainSection section);
    Optional<Ticket> findByUserId(Long userId);
}
