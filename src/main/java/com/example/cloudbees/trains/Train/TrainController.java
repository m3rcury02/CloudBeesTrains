package com.example.cloudbees.trains.Train;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.cloudbees.trains.Data.Receipt;
import com.example.cloudbees.trains.Data.Ticket;
import com.example.cloudbees.trains.Data.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @Operation(summary = "Purchase a train ticket")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Successfully purchased a ticket"),
      @ApiResponse(responseCode = "400", description = "Invalid ticket data")})
    @PostMapping("/purchase")
    public Receipt purchaseTicket(@Valid @RequestBody Ticket ticket) {
        return trainService.purchaseTicket(ticket);
    }

    @Operation(summary = "Get a receipt by user ID")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the receipt"),
      @ApiResponse(responseCode = "404", description = "Receipt not found")})
    @GetMapping("/receipt/{userId}")
    public Receipt getReceipt(@Parameter(description = "User ID") @PathVariable String userId) {
        return trainService.getReceipt(userId);
    }

    @Operation(summary = "Get users by section")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the users"),
      @ApiResponse(responseCode = "400", description = "Invalid section")})
    @GetMapping("/users/{section}")
    public Map<String, String> getUsersBySection(@Parameter(description = "Section (A or B)") @PathVariable String section) {
        return trainService.getUsersBySection(section);
    }

    @Operation(summary = "Remove a user")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Successfully removed the user"),
      @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping("/users/{userId}")
    public void removeUser(@Parameter(description = "User ID") @PathVariable String userId) {
        trainService.removeUser(userId);
    }

    @Operation(summary = "Modify a user's seat")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Successfully modified the user's seat"),
      @ApiResponse(responseCode = "400", description = "Invalid seat"),
      @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping("/users/{userId}")
    public User modifyUserSeat(@Parameter(description = "User ID") @PathVariable String userId, @RequestBody String newSeat) {
        return trainService.modifyUserSeat(userId, newSeat);
    }
}