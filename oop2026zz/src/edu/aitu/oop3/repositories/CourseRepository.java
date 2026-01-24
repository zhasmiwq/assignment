package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course create(Course course);
    Optional<Course> findById(long id);
    List<Course> findAll();
    List<Course> findByTeacher(long teacherId);

    boolean delete(long id);
}