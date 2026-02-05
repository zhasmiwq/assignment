package assignment4.entities;

public abstract class Lesson {
    private final int id;
    private final int courseId;
    private final String title;
    private final LessonType type;

    protected Lesson(int id, int courseId, String title, LessonType type) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.type = type;
    }

    public int getId() { return id; }
    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public LessonType getType() { return type; }
}
