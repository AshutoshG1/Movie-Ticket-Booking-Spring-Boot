package com.codewithashutosh.Repositories;


import com.codewithashutosh.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
