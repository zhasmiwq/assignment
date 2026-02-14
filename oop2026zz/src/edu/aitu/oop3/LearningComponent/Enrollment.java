package edu.aitu.oop3.LearningComponent;
public class Enrollment {
    private long id;
    private long userId;
    private long courseId;

    public Enrollment() {}

    public Enrollment(long id, long userId, long courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
    }

    public long getId() { return id; }
    public long getUserId() { return userId; }
    public long getCourseId() { return courseId; }
}
