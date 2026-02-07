package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Course;
import edu.aitu.oop3.patterns.CourseCatalog;
import edu.aitu.oop3.util.Page;

import java.util.List;

public class CourseSearchService {

    public Page<Course> searchByTag(String tag, int page, int size) {
        List<Course> filtered = CourseCatalog.getInstance().getCourses().stream()
                .filter(c -> c.getTags() != null &&
                        c.getTags().stream().anyMatch(t -> t.equalsIgnoreCase(tag)))
                .toList();

        int from = Math.max(0, page * size);
        int to = Math.min(filtered.size(), from + size);
        List<Course> items = (from >= to) ? List.of() : filtered.subList(from, to);

        return new Page<>(items, page, size, filtered.size());
    }
    public Page<Course> search(String kw, int page, int size) {
        final Page<Course> o = null;
        return o;
    }
}