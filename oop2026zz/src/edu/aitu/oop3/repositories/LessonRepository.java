package edu.aitu.oop3.repositories;

import oop2026_groupIT25XX_online_learning.entities.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Lesson create(Lesson lesson);
    Optional<Lesson> findById(long id);
    List<Lesson> findByCourse(long courseId);
    boolean delete(long id);
}