package com.codewithashutosh.Controllers;


import com.codewithashutosh.Dtos.RequestDtos.UserEntryDto;
import com.codewithashutosh.Dtos.ResponseDtos.TicketResponseDto;
import com.codewithashutosh.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addNewUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            String result = userService.addUser(userEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allTickets/{userId}")
    public ResponseEntity<List<TicketResponseDto>> allTickets(@PathVariable Integer userId) {
        try {
            List<TicketResponseDto> result = userService.allTickets(userId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
