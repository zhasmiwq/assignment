package edu.aitu.oop3.LearningComponent;

import edu.aitu.oop3.UserManagementComponent.NotFoundException;
import edu.aitu.oop3.CourseManagementComponent.CourseRepository;
import edu.aitu.oop3.UserManagementComponent.UserRepository;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             UserRepository userRepo,
                             CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    public void enroll(long userId, long courseId) {
        if (userRepo.findById(userId).isEmpty())
            throw new NotFoundException("User not found");

        if (courseRepo.findById(courseId).isEmpty())
            throw new NotFoundException("Course not found");

        enrollmentRepo.enroll(userId, courseId);
    }
}
