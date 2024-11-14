package com.cloudbees.trainticket.service;

import com.cloudbees.trainticket.model.Ticket;
import com.cloudbees.trainticket.model.User;
import com.cloudbees.trainticket.model.TrainSection;
import com.cloudbees.trainticket.repository.TicketRepository;
import com.cloudbees.trainticket.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTicket() {
        User user = new User();
        user.setFirstName("Aravindh");
        user.setLastName("Thiyagarajan");
        user.setEmail("aravindhan@gmail.com");

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setFromLocation("London");
        ticket.setToLocation("France");
        ticket.setPrice(5.0);
        ticket.setSection(TrainSection.SECTION_A);
        ticket.setSeat("Seat-1");

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket createdTicket = ticketService.createTicket(user, TrainSection.SECTION_A);

        assertNotNull(createdTicket);
        assertEquals("London", createdTicket.getFromLocation());
        assertEquals("France", createdTicket.getToLocation());
        assertEquals(5.0, createdTicket.getPrice());
        assertEquals(TrainSection.SECTION_A, createdTicket.getSection());
        assertNotNull(createdTicket.getSeat());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void testGetTicketByUserId_Found() {
        Long userId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(ticketRepository.findByUserId(userId)).thenReturn(Optional.of(ticket));
        Optional<Ticket> foundTicket = ticketService.getTicketByUserId(userId);
        assertTrue(foundTicket.isPresent());
        assertEquals(1L, foundTicket.get().getId());
    }

    @Test
    public void testGetTicketByUserId_NotFound() {
        Long userId = 1L;
        when(ticketRepository.findByUserId(userId)).thenReturn(Optional.empty());
        Optional<Ticket> foundTicket = ticketService.getTicketByUserId(userId);
        assertFalse(foundTicket.isPresent());
    }

    @Test
    public void testGetTicketsBySection() {
        Ticket ticket1 = new Ticket();
        ticket1.setSection(TrainSection.SECTION_A);
        Ticket ticket2 = new Ticket();
        ticket2.setSection(TrainSection.SECTION_A);

        when(ticketRepository.findBySection(TrainSection.SECTION_A)).thenReturn(Arrays.asList(ticket1, ticket2));
        List<Ticket> tickets = ticketService.getTicketsBySection(TrainSection.SECTION_A);
        assertNotNull(tickets);
        assertEquals(2, tickets.size());
        verify(ticketRepository, times(1)).findBySection(TrainSection.SECTION_A);
    }

    @Test
    public void testDeleteTicket() {
        Long userId = 1L;
        doNothing().when(ticketRepository).deleteById(userId);
        ticketService.deleteTicket(userId);
        verify(ticketRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testUpdateSeat_Success() {
        Long userId = 1L;
        String newSeat = "Seat-12";
        Ticket ticket = new Ticket();
        ticket.setUser(new User());
        ticket.setSeat("Seat-1");

        when(ticketRepository.findByUserId(userId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        Ticket updatedTicket = ticketService.updateSeat(userId, newSeat);
        assertEquals(newSeat, updatedTicket.getSeat());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void testUpdateSeat_UserNotFound() {
        Long userId = 1L;
        String newSeat = "Seat-12";

        when(ticketRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ticketService.updateSeat(userId, newSeat));
    }
}
