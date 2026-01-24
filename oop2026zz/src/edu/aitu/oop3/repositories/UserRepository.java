package edu.aitu.oop3.repositories;

import oop2026_groupIT25XX_online_learning.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}