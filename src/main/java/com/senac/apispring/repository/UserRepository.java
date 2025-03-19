package com.senac.apispring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.apispring.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
