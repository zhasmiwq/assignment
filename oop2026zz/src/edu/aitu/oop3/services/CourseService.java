package edu.aitu.oop3.services;

import oop2026_groupIT25XX_online_learning.entities.Course;
import oop2026_groupIT25XX_online_learning.exceptions.NotFoundException;
import oop2026_groupIT25XX_online_learning.repositories.CourseRepository;
import oop2026_groupIT25XX_online_learning.repositories.UserRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public CourseService(CourseRepository courseRepo, UserRepository userRepo) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    public Course createCourse(String title, String description, long teacherId) {
        if (title == null || title.trim().length() < 3)
            throw new IllegalArgumentException("Title must be at least 3 chars");

        var teacherOpt = userRepo.findById(teacherId);
        if (teacherOpt.isEmpty()) throw new NotFoundException("Teacher not found: id=" + teacherId);
        if (!"TEACHER".equals(teacherOpt.get().getRole()))
            throw new IllegalArgumentException("This user is not TEACHER");

        return courseRepo.create(new Course(0, title.trim(), description, teacherId));
    }

    public List<Course> listCourses() {
        return courseRepo.findAll();
    }

    public Course getCourse(long id) {
        return courseRepo.findById(id).orElseThrow(() -> new NotFoundException("Course not found: id=" + id));
    }
}