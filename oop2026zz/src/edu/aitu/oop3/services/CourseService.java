package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.UserRepository;

import javax.naming.Name;
import java.util.ArrayList;
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
    public static Builder builder() { return new Builder(); }

    public Course createCourse(Course build) {
        return null;
    }

    public static final class Builder {
        private long id;
        private String title;
        private String description;
        private long teacherId;
        private final List<Lesson> lessons = new ArrayList<>();
        private final List<String> tags = new ArrayList<>();
        private boolean archived;

        public Builder id(long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder teacherId(long teacherId) { this.teacherId = teacherId; return this; }
        public Builder addLesson(Lesson lesson) { if (lesson != null) this.lessons.add(lesson); return this; }
        public Builder addTag(String tag) { if (tag != null && !tag.isBlank()) this.tags.add(tag.trim()); return this; }
        public Builder archived(boolean archived) { this.archived = archived; return this; }

        public Course build() {
            if (title == null || title.trim().length() < 3) {
                throw new IllegalStateException("Course title must be at least 3 characters");
            }
            Course c = new Course(id, title.trim(), description, teacherId);
            c.tags.addAll(this.tags);
            c.archived = this.archived;
            return c;
        }
    }
}
