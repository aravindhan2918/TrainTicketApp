package com.cloudbees.trainticket.controller;


import com.cloudbees.trainticket.model.User;
import com.cloudbees.trainticket.model.Ticket;
import com.cloudbees.trainticket.model.TrainSection;
import com.cloudbees.trainticket.repository.UserRepository;
import com.cloudbees.trainticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserRepository userRepository;

    /**
     * API endpoint to purchase a ticket for a train journey.
     */
    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody User user, @RequestParam TrainSection section) {
        userRepository.save(user); // Save user details
        return ticketService.createTicket(user, section);
    }

    /**
     * API endpoint to fetch ticket details of a user.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getTicket(@PathVariable Long userId) {
        Optional<Ticket> ticket = ticketService.getTicketByUserId(userId);
        if (!ticket.isPresent()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id - " + userId + " not found !");
        }
        return ResponseEntity.ok(ticket);
    }

    /**
     * API endpoint to remove user from the train.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId) {
        ticketService.deleteTicket(userId);
        return ResponseEntity.ok("User ID - " + userId + " removed successfully.");
    }

    /**
     * API endpoint to modify user.
     */
    @PatchMapping("/{userId}/seat")
    public ResponseEntity<Ticket> patchSeat(@PathVariable Long userId, @RequestParam String newSeat) {
        return ResponseEntity.ok(ticketService.updateSeat(userId, newSeat));
    }

}



