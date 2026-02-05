package edu.aitu.oop3.entities;
public final class TextLesson extends assignment4.entities.Lesson {
    private final String text;

    public TextLesson(int id, int courseId, String title, String text) {
        super(id, courseId, title, LessonType.TEXT);
        this.text = text;
    }

    public String getText() { return text; }
}
