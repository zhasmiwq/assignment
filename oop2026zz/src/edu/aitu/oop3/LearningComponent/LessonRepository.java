package edu.aitu.oop3.LearningComponent;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Lesson create(Lesson lesson);


    Optional<Lesson> findById(long id);
    List<Lesson> findByCourse(long courseId);
    boolean delete(long id);
}