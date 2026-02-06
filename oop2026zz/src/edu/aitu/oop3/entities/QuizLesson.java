package edu.aitu.oop3.entities;

public final class QuizLesson extends Lesson {
    private final int questionsCount;

    public QuizLesson(int id, int courseId, String title, int questionsCount) {
        super(id, courseId, title, String.valueOf(LessonType.QUIZ));
        this.questionsCount = questionsCount;
    }

    public int getQuestionsCount() { return questionsCount; }
}