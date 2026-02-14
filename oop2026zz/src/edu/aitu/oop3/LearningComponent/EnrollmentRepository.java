package edu.aitu.oop3.LearningComponent;

import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment enroll(long userId, long courseId);
    Optional<Enrollment> find(long userId, long courseId);
}
