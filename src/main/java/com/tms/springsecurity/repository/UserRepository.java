package com.tms.springsecurity.repository;

import com.tms.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
Optional<User> findByLogin(String login);

Optional<User> findByPerm(String username);
}
