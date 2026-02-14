package edu.aitu.oop3.CourseManagementComponent;

import edu.aitu.oop3.LearningComponent.Lesson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {
    private int id;
    private final String title;
    private final String description;
    private final long teacherId;
    private final List<Lesson> lessons;
    private final List<String> tags;
    private final boolean archived;


    public Course(Builder b) {
        this.id = b.id;
        this.title = b.title;
        this.description = b.description;
        this.teacherId = b.teacherId;
        this.lessons = new ArrayList<>(b.lessons);
        this.tags = new ArrayList<>(b.tags);
        this.archived = b.archived;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public long getTeacherId() { return teacherId; }
    public List<Lesson> getLessons() { return Collections.unmodifiableList(lessons); }
    public List<String> getTags() { return Collections.unmodifiableList(tags); }
    public boolean isArchived() { return archived; }

    public void setId(long id) {
        this.id = (int) id;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private long teacherId;
        private final List<Lesson> lessons = new ArrayList<>();
        private final List<String> tags = new ArrayList<>();
        private boolean archived;

        public Builder id(int id) { this.id = id; return this; }
        public Builder title(String t) { this.title = t; return this; }
        public Builder description(String d) { this.description = d; return this; }
        public Builder teacherId(long teacherId) { this.teacherId = teacherId; return this; }
        public Builder addLesson(Lesson l) { this.lessons.add(l); return this; }
        public void addTag(String tag) { this.tags.add(tag.trim());
        }
        public Builder archived(boolean v) { this.archived = v; return this; }

        public Course build() {
            if (title == null || title.isBlank())
                throw new IllegalStateException("Course title is required");
            if (teacherId <= 0)
                throw new IllegalStateException("teacherId is required");
            return new Course(this);
        }
    }
}
