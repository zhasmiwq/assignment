package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.entities.LessonType;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.LessonRepository;

import java.util.List;

public class LessonService {
    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    public LessonService(LessonRepository lessonRepo, CourseRepository courseRepo) {
        this.lessonRepo = lessonRepo;
        this.courseRepo = courseRepo;
    }

    public Lesson addLesson(long courseId, LessonType type, String title, String content) {
        if (title == null || title.trim().length() < 3)
            throw new IllegalArgumentException("Lesson title must be at least 3 chars");

        if (courseRepo.findById(courseId).isEmpty())
            throw new NotFoundException("Course not found: id=" + courseId);

        return lessonRepo.create(new Lesson(0, courseId, title.trim(), content));
    }


    public List<Lesson> listLessons(long courseId) {
        if (courseRepo.findById(courseId).isEmpty())
            throw new NotFoundException("Course not found: id=" + courseId);

        return lessonRepo.findByCourse(courseId);
    }
}