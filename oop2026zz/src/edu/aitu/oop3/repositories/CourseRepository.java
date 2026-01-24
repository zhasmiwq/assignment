package edu.aitu.oop3.repositories;

import oop2026_groupIT25XX_online_learning.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course create(Course course);
    Optional<Course> findById(long id);
    List<Course> findAll();
    List<Course> findByTeacher(long teacherId);
}