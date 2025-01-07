package br.com.backend.ccx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.backend.ccx.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
