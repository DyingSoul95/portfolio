package ru.kolotovkin.lastproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolotovkin.lastproject.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAll();

    List<Message> findByAuthorName(String name);
}
