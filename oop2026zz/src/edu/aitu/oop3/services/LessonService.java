package edu.aitu.oop3.services;

import oop2026_groupIT25XX_online_learning.entities.Lesson;
import oop2026_groupIT25XX_online_learning.exceptions.NotFoundException;
import oop2026_groupIT25XX_online_learning.repositories.CourseRepository;
import oop2026_groupIT25XX_online_learning.repositories.LessonRepository;

import java.util.List;

public class LessonService {
    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    public LessonService(LessonRepository lessonRepo, CourseRepository courseRepo) {
        this.lessonRepo = lessonRepo;
        this.courseRepo = courseRepo;
    }

    public Lesson addLesson(long courseId, String title, String content, int position) {
        if (title == null || title.trim().length() < 3)
            throw new IllegalArgumentException("Lesson title must be at least 3 chars");
        if (position <= 0)
            throw new IllegalArgumentException("Position must be > 0");

        if (courseRepo.findById(courseId).isEmpty())
            throw new NotFoundException("Course not found: id=" + courseId);

        return lessonRepo.create(new Lesson(0, courseId, title.trim(), content, position));
    }

    public List<Lesson> listLessons(long courseId) {
        if (courseRepo.findById(courseId).isEmpty())
            throw new NotFoundException("Course not found: id=" + courseId);
        return lessonRepo.findByCourse(courseId);
    }
}