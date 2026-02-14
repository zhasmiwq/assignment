package edu.aitu.oop3.LearningComponent;
import edu.aitu.oop3.UserManagementComponent.NotFoundException;

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
