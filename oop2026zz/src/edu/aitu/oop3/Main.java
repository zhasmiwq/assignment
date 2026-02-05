package edu.aitu.oop3;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.data.PostgresDB;
import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.entities.User;
import edu.aitu.oop3.exceptions.DatabaseException;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.CourseService;
import edu.aitu.oop3.services.LessonService;
import edu.aitu.oop3.services.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db;
        db = new PostgresDB();


        UserRepository userRepo = new UserRepositoryImpl(db);
        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        LessonRepository lessonRepo = new LessonRepositoryImpl(db);

        UserService userService = new UserService(userRepo);
        CourseService courseService = new CourseService(courseRepo, userRepo);
        LessonService lessonService = new LessonService(lessonRepo, courseRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Online Learning Platform ===");
            System.out.println("1) Register user");
            System.out.println("2) Create course");
            System.out.println("3) Add lesson");
            System.out.println("4) Show USERS");
            System.out.println("5) Show COURSES");
            System.out.println("6) Show LESSONS by courseId");
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

                        Course c = courseService.createCourse(title, desc, teacherId);
                        System.out.println("OK course id=" + c.getId());
                    }
                    case "3" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());

                        System.out.print("Lesson title: ");
                        String title = sc.nextLine();
                        System.out.print("Content: ");
                        String content = sc.nextLine();

                        Lesson l = lessonService.addLesson(courseId, title, content);
                        System.out.println("OK lesson id=" + l.getId());
                    }
                    case "4" -> printUsers(userService.listUsers());
                    case "5" -> printCourses(courseService.listCourses());
                    case "6" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());
                        printLessons(lessonService.listLessons(courseId), courseId);
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

    private static void printUsers(List<User> users) {}
    private static void printCourses(List<Course> courses) {}
    private static void printLessons(List<Lesson> lessons, long courseId) {}
    private static void cut(String s, int max) {}
}
