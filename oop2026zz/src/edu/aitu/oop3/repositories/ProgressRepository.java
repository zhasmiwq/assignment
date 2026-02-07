package edu.aitu.oop3.repositories;

import edu.aitu.oop3.entities.Progress;
import java.util.List;
import java.util.Optional;

public interface ProgressRepository {

    void markCompleted(long userId, long lessonId);

    Optional<Progress> find(long userId, long lessonId);

    List<Progress> findByUserAndCourse(long userId, long courseId);
}
