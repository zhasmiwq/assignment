package edu.aitu.oop3.entities;

public final class VideoLesson extends Lesson {
    private final String videoUrl;

    public VideoLesson(int id, int courseId, String title, String videoUrl) {
        super(id, courseId, title, LessonType.VIDEO);
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() { return videoUrl; }
}