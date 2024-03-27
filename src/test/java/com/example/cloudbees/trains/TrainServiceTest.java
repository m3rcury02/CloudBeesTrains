package com.example.cloudbees.trains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.cloudbees.trains.Data.Receipt;
import com.example.cloudbees.trains.Data.Ticket;
import com.example.cloudbees.trains.Data.User;
import com.example.cloudbees.trains.Train.TrainService;

@SpringBootTest
public class TrainServiceTest {

    @InjectMocks
    private TrainService trainService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseTicket() {
        Ticket ticket = new Ticket();
        ticket.setFrom("London");
        ticket.setTo("France");
        ticket.setPrice(20.0);
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        ticket.setUser(user);

        Receipt receipt = trainService.purchaseTicket(ticket);

        assertNotNull(receipt);
        assertEquals("London", receipt.getFrom());
        assertEquals("France", receipt.getTo());
        assertEquals(20.0, receipt.getPricePaid());
        assertEquals(user, receipt.getUser());
    }
@Test
public void testGetUsersBySection() {
    try {
        Map<String, String> users = trainService.getUsersBySection("nonexistent");
        fail("Expected an ResponseStatusException to be thrown");
    } catch (ResponseStatusException e) {
        assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }
}

@Test
public void testGetReceipt() {
    try {
        Receipt receipt = trainService.getReceipt("nonexistent");
        fail("Expected an ResponseStatusException to be thrown");
    } catch (ResponseStatusException e) {
        assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }
}

    @Test
    public void testRemoveUser() {
        assertThrows(ResponseStatusException.class, () -> {
            trainService.removeUser("nonexistent");
        });
    }

    @Test
    public void testModifyUserSeat() {
        assertThrows(ResponseStatusException.class, () -> {
            trainService.modifyUserSeat("nonexistent", "A1");
        });
    }
}