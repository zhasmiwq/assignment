package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}