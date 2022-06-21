package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.merin.moviebooking.entity.User;

public interface IAdminRepository extends JpaRepository<User, Integer>
{

}
