package ru.kolotovkin.lastproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolotovkin.lastproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

