package edu.aitu.oop3.entities;
public class Progress {
    private long id;
    private long userId;
    private long lessonId;
    private boolean completed;

    public Progress() {}

    public Progress(long id, long userId, long lessonId, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.lessonId = lessonId;
        this.completed = completed;
    }

    public long getId() { return id; }
    public long getUserId() { return userId; }
    public long getLessonId() { return lessonId; }
    public boolean isCompleted() { return completed; }
}

