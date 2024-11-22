package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Chat;
import com.rafaelaugustor.flashwork.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    @Query("SELECT c FROM Chat c WHERE :userOne MEMBER OF c.users AND :userTwo MEMBER OF c.users")
    Optional<Chat> findByUsers(User userOne, User userTwo);

    List<Chat> findAllByUsersContains(User user);
}
