package edu.aitu.oop3.components;
import edu.aitu.oop3.entities.User;
import edu.aitu.oop3.services.UserService;

import java.util.List;

public class UserManagementComponent {
    private final UserService userService;

    public UserManagementComponent(UserService userService) {
        this.userService = userService;
    }

    public User register(String fullName, String email, String role) {
        return userService.register(fullName, email, role);
    }

    public List<User> listUsers() {
        return userService.listUsers();
    }

    public User getUser(long id) {
        return userService.getUser(id);
    }
}
