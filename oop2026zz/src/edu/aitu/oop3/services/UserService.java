package edu.aitu.oop3.services;

import oop2026_groupIT25XX_online_learning.entities.User;
import oop2026_groupIT25XX_online_learning.exceptions.NotFoundException;
import oop2026_groupIT25XX_online_learning.repositories.UserRepository;

public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(String fullName, String email, String role) {
        if (fullName == null || fullName.trim().length() < 3)
            throw new IllegalArgumentException("Full name must be at least 3 chars");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email");
        if (!"STUDENT".equals(role) && !"TEACHER".equals(role))
            throw new IllegalArgumentException("Role must be STUDENT or TEACHER");

        return repo.create(new User(0, fullName.trim(), email.trim(), role));
    }

    public User getUser(long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("User not found: id=" + id));
    }
}