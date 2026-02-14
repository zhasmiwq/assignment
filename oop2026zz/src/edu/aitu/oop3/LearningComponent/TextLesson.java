package edu.aitu.oop3.LearningComponent;

public final class TextLesson extends Lesson {
    private final String text;

    public TextLesson(int id, int courseId, String title, String text) {
        super(id, courseId, title, String.valueOf(LessonType.TEXT));
        this.text = text;
    }

    public String getText() { return text; }
}
