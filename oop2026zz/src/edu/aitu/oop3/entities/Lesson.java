package edu.aitu.oop3.entities;
public class Lesson {
    private long id;
    private long courseId;
    private String title;
    private String content;
    private int position;

    public Lesson(long id, long courseId, String title, String content) {}

    public Lesson(long id, long courseId, String title, String content, int position) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.content = content;
        this.position = position;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getCourseId() { return courseId; }
    public void setCourseId(long courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
}
