package edu.aitu.oop3.entities;
public class Course {
    private long id;
    private String title;
    private String description;
    private long teacherId;

    public Course() {}

    public Course(long id, String title, String description, long teacherId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getTeacherId() { return teacherId; }
    public void setTeacherId(long teacherId) { this.teacherId = teacherId; }
}
