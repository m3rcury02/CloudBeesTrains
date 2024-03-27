package com.example.cloudbees.trains.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.cloudbees.trains.Data.Receipt;
import com.example.cloudbees.trains.Data.Ticket;
import com.example.cloudbees.trains.Data.User;

@Service
public class TrainService {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Receipt> receipts = new HashMap<>();
    private Map<String, String> seatAllocationsA = new HashMap<>();
    private Map<String, String> seatAllocationsB = new HashMap<>();
    private int lastSeatA = 0;
    private int lastSeatB = 0;

    public Receipt purchaseTicket(Ticket ticket) {
        if (ticket.getPrice() != 20.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ticket price");
        }

        String userId = UUID.randomUUID().toString();
        User user = ticket.getUser();
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user information");
        }
        users.put(userId, user);

        String section;
        if (lastSeatA <= lastSeatB) {
            section = "A";
            seatAllocationsA.put(userId, "A" + (++lastSeatA));
        } else {
            section = "B";
            seatAllocationsB.put(userId, "B" + (++lastSeatB));
        }

        Receipt receipt = new Receipt();
        receipt.setUserId(userId);
        receipt.setFrom(ticket.getFrom());
        receipt.setTo(ticket.getTo());
        receipt.setUser(user);
        receipt.setPricePaid(ticket.getPrice());
        receipt.setSeat(section + (section.equals("A") ? lastSeatA : lastSeatB));

        receipts.put(userId, receipt);

        return receipt;
    }

    public Receipt getReceipt(String userId) {
        Receipt receipt = receipts.get(userId);
        if (receipt == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receipt not found");
        }
        return receipt;
    }

    public Map<String, String> getUsersBySection(String section) {
        if ("A".equalsIgnoreCase(section)) {
            return seatAllocationsA;
        } else if ("B".equalsIgnoreCase(section)) {
            return seatAllocationsB;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid section");
        }
    }

    public void removeUser(String userId) {
        if (!users.containsKey(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        users.remove(userId);
        receipts.remove(userId);
        seatAllocationsA.remove(userId);
        seatAllocationsB.remove(userId);
    }

    public User modifyUserSeat(String userId, String newSeat) {
        User user = users.get(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (newSeat.startsWith("A")) {
            seatAllocationsA.put(userId, newSeat);
            seatAllocationsB.remove(userId);
        } else if (newSeat.startsWith("B")) {
            seatAllocationsB.put(userId, newSeat);
            seatAllocationsA.remove(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid seat");
        }

        return user;
    }
}
