package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.entities.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Lesson create(Lesson lesson);


    Optional<Lesson> findById(long id);
    List<Lesson> findByCourse(long courseId);
    boolean delete(long id);
}