package edu.aitu.oop3.CourseManagementComponent;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course create(Course course);
    Optional<Course> findById(long id);
    List<Course> findAll();
    List<Course> findByTeacher(long teacherId);

    boolean delete(long id);
}