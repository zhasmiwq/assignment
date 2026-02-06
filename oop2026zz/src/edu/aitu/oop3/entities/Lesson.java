package edu.aitu.oop3.entities;
public class Lesson {
    private final int id;
    private final int courseId;
    private final String title;
    private final String type;

    public Lesson(long id, long courseId, String title, String type) {
        this.id = (int) id;
        this.courseId = (int) courseId;
        this.title = title;
        this.type = type;
    }
    public int getId() { return id; }
    public long getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getType() { return type; }

    public void setId(long aLong) {
    }

    public String getContent() {
        return null;
    }
}
