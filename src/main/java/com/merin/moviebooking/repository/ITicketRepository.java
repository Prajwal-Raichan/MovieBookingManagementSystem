package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer>
{

}
