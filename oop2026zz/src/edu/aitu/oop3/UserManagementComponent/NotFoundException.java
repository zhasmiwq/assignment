package edu.aitu.oop3.UserManagementComponent;
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}