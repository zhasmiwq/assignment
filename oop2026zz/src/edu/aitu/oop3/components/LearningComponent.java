package edu.aitu.oop3.components;

import edu.aitu.oop3.LearningComponent.Lesson;
import edu.aitu.oop3.LearningComponent.LessonType;
import edu.aitu.oop3.LearningComponent.LessonService;
import edu.aitu.oop3.LearningComponent.ProgressService;

import java.util.List;

public class LearningComponent {

    private final LessonService lessonService;
    private final ProgressService progressService;

    public LearningComponent(LessonService lessonService, ProgressService progressService) {
        this.lessonService = lessonService;
        this.progressService = progressService;
    }

    public Lesson addLesson(long courseId, LessonType type, String title, String payload) {
        return lessonService.addLesson(courseId, type, title, payload);
    }

    public List<Lesson> listLessons(long courseId) {
        return lessonService.listLessons(courseId);
    }

    public void completeLesson(long userId, long courseId, long lessonId) {
        progressService.completeLesson(userId, courseId, lessonId);
    }
}
