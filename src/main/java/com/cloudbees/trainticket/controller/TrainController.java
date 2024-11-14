package com.cloudbees.trainticket.controller;

import com.cloudbees.trainticket.model.Ticket;
import com.cloudbees.trainticket.model.TrainSection;
import com.cloudbees.trainticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TicketService ticketService;

    /**
     * API endpoint to fetch ticket details of users from requested section.
     */
    @GetMapping("/section/{section}")
    public ResponseEntity<?> getUsersBySection(@PathVariable TrainSection section) {
        List<Ticket> tickets = ticketService.getTicketsBySection(section);
        if (tickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tickets found for " + section + ".");
        }
        return ResponseEntity.ok(tickets);
    }

}
