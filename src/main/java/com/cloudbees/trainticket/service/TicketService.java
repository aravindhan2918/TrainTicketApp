package com.cloudbees.trainticket.service;

import com.cloudbees.trainticket.model.User;
import com.cloudbees.trainticket.model.Ticket;
import com.cloudbees.trainticket.model.TrainSection;
import com.cloudbees.trainticket.repository.TicketRepository;
import com.cloudbees.trainticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    public Ticket createTicket(User user, TrainSection section) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setFromLocation("London");
        ticket.setToLocation("France");
        ticket.setPrice(5.0);
        ticket.setSection(section);
        ticket.setSeat(assignSeat(section));
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getTicketsBySection(TrainSection section) {
        return ticketRepository.findBySection(section);
    }

    public void deleteTicket(Long userId) {
        ticketRepository.deleteById(userId);
    }

    public Ticket updateSeat(Long userId, String newSeat) {
        Ticket ticket = ticketRepository.findByUserId(userId).orElseThrow();
        ticket.setSeat(newSeat);
        return ticketRepository.save(ticket);
    }

    private String assignSeat(TrainSection section) {
        return "Seat-" + (new Random().nextInt(50) + 1);
    }
}
