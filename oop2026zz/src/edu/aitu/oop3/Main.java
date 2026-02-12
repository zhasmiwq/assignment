package edu.aitu.oop3;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.data.PostgresDB;
import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.entities.LessonType;
import edu.aitu.oop3.entities.User;
import edu.aitu.oop3.exceptions.DatabaseException;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.patterns.CourseCatalog;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.CourseSearchService;
import edu.aitu.oop3.services.CourseService;
import edu.aitu.oop3.services.LessonService;
import edu.aitu.oop3.services.UserService;
import edu.aitu.oop3.util.Page;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();

        UserRepository userRepo = new UserRepositoryImpl(db);
        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        LessonRepository lessonRepo = new LessonRepositoryImpl(db);

        UserService userService = new UserService(userRepo);
        CourseService courseService = new CourseService(courseRepo, userRepo);
        LessonService lessonService = new LessonService(lessonRepo, courseRepo);

        CourseCatalog.getInstance().attachRepository(courseRepo);

        CourseSearchService courseSearchService = new CourseSearchService();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Online Learning Platform ===");
            System.out.println("1) Register user");
            System.out.println("2) Create course");
            System.out.println("3) Add lesson");
            System.out.println("4) Add lesson");
            System.out.println("5) Show USERS");
            System.out.println("6) Show COURSES");
            System.out.println("7) Show LESSONS by courseId");
            System.out.println("8) Search courses");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String ch = sc.nextLine().trim();

            try {
                switch (ch) {
                    case "1" -> {
                        System.out.print("Full name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Role (STUDENT/TEACHER): ");
                        String role = sc.nextLine().trim().toUpperCase();

                        User u = userService.register(name, email, role);
                        System.out.println("OK user id=" + u.getId());
                    }
                    case "2" -> {
                        System.out.print("Teacher id: ");
                        long teacherId = Long.parseLong(sc.nextLine());
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Tags (comma separated, optional): ");
                        String tagsLine = sc.nextLine();

                        Course.Builder b = Course.builder()
                                .title(title)
                                .description(desc)
                                .teacherId(teacherId);

                        if (tagsLine != null && !tagsLine.isBlank()) {
                            for (String t : tagsLine.split(",")) b.addTag(t);
                        }

                        Course created = courseService.createCourse(b.build());
                        System.out.println("OK course id=" + created.getId());
                    }
                    case "3" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());

                        System.out.print("Lesson title: ");
                        String title = sc.nextLine();
                        System.out.print("Content: ");
                        String content = sc.nextLine();

                        LessonType type = null;
                        Lesson l = lessonService.addLesson(courseId, type, title, content);
                        System.out.println("OK lesson id=" + l.getId());
                    }
                    case "4" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());

                        System.out.print("Type (VIDEO/TEXT/QUIZ): ");
                        LessonType type = LessonType.valueOf(sc.nextLine().trim().toUpperCase());

                        System.out.print("Lesson title: ");
                        String title = sc.nextLine();

                        System.out.print("Payload (VIDEO=url, TEXT=text, QUIZ=number): ");
                        String payload = sc.nextLine();

                        Lesson l = lessonService.addLesson(courseId, type, title, payload);
                        System.out.println("OK lesson id=" + l.getId() + " (type=" + type + ")");
                    }
                    case "5" -> printUsers(userService.listUsers());
                    case "6" -> printCourses(courseService.listCourses());
                    case "7" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());
                        printLessons(lessonService.listLessons(courseId), courseId);
                    }
                    case "8" -> {
                        // refresh catalog from DB
                        CourseCatalog.getInstance().refresh();

                        System.out.print("Keyword (title/description): ");
                        String kw = sc.nextLine();

                        System.out.print("Page number (0..): ");
                        int page = Integer.parseInt(sc.nextLine());

                        System.out.print("Page size: ");
                        int size = Integer.parseInt(sc.nextLine());

                        Page<Course> result = courseSearchService.search(kw, page, size);
                        System.out.println("Total items: " + result.getTotalItems() +
                                ", total pages: " + result.getTotalPages());

                        result.getItems().forEach(c ->
                                System.out.println("[" + c.getId() + "] " + c.getTitle() + " (teacherId=" + c.getTeacherId() + ")")
                        );
                    }
                    case "0" -> { System.out.println("Bye"); return; }
                    default -> System.out.println("Unknown option");
                }
            } catch (NotFoundException e) {
                System.out.println("NOT FOUND: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("VALIDATION: " + e.getMessage());
            } catch (DatabaseException e) {
                System.out.println("DB ERROR: " + e.getMessage());
            }
        }
    }

    private static void printUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("(no users)");
            return;
        }
        for (User u : users) {
            System.out.println("[" + u.getId() + "] " + u.getFullName() + " | " + u.getEmail() + " | " + u.getRole());
        }
    }

    private static void printCourses(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            System.out.println("(no courses)");
            return;
        }
        for (Course c : courses) {
            System.out.println("[" + c.getId() + "] " + c.getTitle() + " | teacherId=" + c.getTeacherId());
        }
    }

    private static void printLessons(List<Lesson> lessons, long courseId) {
        System.out.println("Lessons for courseId=" + courseId + ":");
        if (lessons == null || lessons.isEmpty()) {
            System.out.println("(no lessons)");
            return;
        }
        for (Lesson l : lessons) {
            System.out.println("[" + l.getId() + "] " + l.getTitle());
        }
    }
}
