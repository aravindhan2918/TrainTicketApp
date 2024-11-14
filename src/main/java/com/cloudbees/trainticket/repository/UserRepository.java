package com.cloudbees.trainticket.repository;

import com.cloudbees.trainticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}


