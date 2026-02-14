package edu.aitu.oop3.CourseManagementComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CourseCatalog {
    private static final CourseCatalog INSTANCE = new CourseCatalog();

    private final List<Course> cache = new ArrayList<>();
    private CourseRepository courseRepo; // сохраним репозиторий

    private CourseCatalog() {}

    public static CourseCatalog getInstance() {
        return INSTANCE;
    }

    public void setCourses(List<Course> courses) {
        cache.clear();
        cache.addAll(courses);
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(cache);
    }


    public void attachRepository(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }


    public void refresh() {
        if (courseRepo == null) {
            throw new IllegalStateException("CourseRepository is not attached");
        }
        setCourses(courseRepo.findAll());
    }
}
