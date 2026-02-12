package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.UserRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public CourseService(CourseRepository courseRepo, UserRepository userRepo) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }
    public Course createCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("Course is null");

        String title = course.getTitle();
        if (title == null || title.trim().length() < 3)
            throw new IllegalArgumentException("Title must be at least 3 chars");

        long teacherId = course.getTeacherId();

        var teacherOpt = userRepo.findById(teacherId);
        if (teacherOpt.isEmpty()) throw new NotFoundException("Teacher not found: id=" + teacherId);
        if (!"TEACHER".equals(teacherOpt.get().getRole()))
            throw new IllegalArgumentException("This user is not TEACHER");

        return courseRepo.create(course);
    }


    public List<Course> listCourses() {
        return courseRepo.findAll();
    }

    public Course getCourse(long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found: id=" + id));
    }
}
