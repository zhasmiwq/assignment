package edu.aitu.oop3.services;
import edu.aitu.oop3.entities.Progress;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.EnrollmentRepository;
import edu.aitu.oop3.repositories.LessonRepository;
import edu.aitu.oop3.repositories.ProgressRepository;

public class ProgressService {
    private final ProgressRepository progressRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final LessonRepository lessonRepo;

    public ProgressService(ProgressRepository progressRepo,
                           EnrollmentRepository enrollmentRepo,
                           LessonRepository lessonRepo) {
        this.progressRepo = progressRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.lessonRepo = lessonRepo;
    }

    public void completeLesson(long userId, long courseId, long lessonId) {
        if (lessonRepo.findById(lessonId).isEmpty())
            throw new NotFoundException("Lesson not found");

        if (enrollmentRepo.find(userId, courseId).isEmpty())
            throw new IllegalStateException("User not enrolled");

    }
}
