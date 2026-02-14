package edu.aitu.oop3.components;

import edu.aitu.oop3.CourseManagementComponent.Course;
import edu.aitu.oop3.CourseManagementComponent.CourseService;

import java.util.List;

public class CourseManagementComponent {
    private final CourseService courseService;

    public CourseManagementComponent(CourseService courseService) {
        this.courseService = courseService;
    }

    public Course createCourse(Course course) {
        return courseService.createCourse(course);
    }

    public List<Course> listCourses() {
        return courseService.listCourses();
    }

    public Course getCourse(long id) {
        return courseService.getCourse(id);
    }

    public boolean archiveCourse(long courseId) {
        return false;
    }
}
