import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.data.PostgresDB;
import edu.aitu.oop3.exceptions.DatabaseException;
import edu.aitu.oop3.exceptions.NotFoundException;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.CourseService;
import edu.aitu.oop3.services.LessonService;
import edu.aitu.oop3.services.UserService;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        IDB db = new PostgresDB(
                "jdbc:postgresql://localhost:5432/online_learning",
                "postgres",
                "password"
        );

        UserRepository userRepo = new UserRepositoryImpl(db);
        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        LessonRepository lessonRepo = new LessonRepositoryImpl(db);

        UserService userService = new UserService(userRepo);
        CourseService courseService = new CourseService(courseRepo, userRepo);
        LessonService lessonService = new LessonService(lessonRepo, courseRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Online Learning Platform ===");
            System.out.println("1) Register user (STUDENT/TEACHER)");
            System.out.println("2) Create course");
            System.out.println("3) List courses");
            System.out.println("4) Add lesson to course");
            System.out.println("5) List lessons by course");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Full name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Role (STUDENT/TEACHER): ");
                        String role = sc.nextLine().trim().toUpperCase();

                        var u = userService.register(name, email, role);
                        System.out.println("OK user id=" + u.getId());
                    }
                    case "2" -> {
                        System.out.print("Teacher id: ");
                        long teacherId = Long.parseLong(sc.nextLine());
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();

                        var c = courseService.createCourse(title, desc, teacherId);
                        System.out.println("OK course id=" + c.getId());
                    }
                    case "3" -> {
                        var list = courseService.listCourses();
                        if (list.isEmpty()) System.out.println("No courses");
                        else for (var c : list)
                            System.out.println(c.getId() + ") " + c.getTitle() + " (teacherId=" + c.getTeacherId() + ")");
                    }
                    case "4" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());
                        System.out.print("Lesson title: ");
                        String lt = sc.nextLine();
                        System.out.print("Content: ");
                        String lc = sc.nextLine();
                        System.out.print("Position (1..n): ");
                        int pos = Integer.parseInt(sc.nextLine());

                        var l = lessonService.addLesson(courseId, lt, lc, pos);
                        System.out.println("OK lesson id=" + l.getId());
                    }
                    case "5" -> {
                        System.out.print("Course id: ");
                        long courseId = Long.parseLong(sc.nextLine());

                        var lessons = lessonService.listLessons(courseId);
                        if (lessons.isEmpty()) System.out.println("No lessons");
                        else for (var l : lessons)
                            System.out.println(l.getPosition() + ". " + l.getTitle() + " (id=" + l.getId() + ")");
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
            } catch (Exception e) {
                System.out.println("UNEXPECTED: " + e.getMessage());
            }
        }
    }
}
