package edu.aitu.oop3.LearningComponent;

public final class LessonFactory {
    private LessonFactory() {}

    public static Lesson create(LessonType type, int id, int courseId, String title, String payload) {
        return switch (type) {
            case VIDEO -> new VideoLesson(id, courseId, title, payload);
            case TEXT  -> new TextLesson(id, courseId, title, payload);
            case QUIZ  -> new QuizLesson(id, courseId, title, Integer.parseInt(payload));
        };
    }
}
