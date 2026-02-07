package edu.aitu.oop3.patterns;
import edu.aitu.oop3.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBuilder {
    private int id;
    private String title;
    private int teacherId;
    private final List<String> tags = new ArrayList<>();

    public CourseBuilder id(int id) {
        this.id = id;
        return this;
    }

    public CourseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder teacherId(int teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public CourseBuilder addTag(String tag) {
        this.tags.add(tag);
        return this;
    }
}
