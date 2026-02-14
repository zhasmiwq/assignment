package edu.aitu.oop3.CourseManagementComponent;
import java.util.List;

public final class Page<T> {
    private final List<T> items;
    private final int pageNumber;
    private final int pageSize;
    private final long totalItems;

    public Page(List<T> items, int pageNumber, int pageSize, long totalItems) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public List<T> getItems() { return items; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public long getTotalItems() { return totalItems; }

    public long getTotalPages() {
        return (totalItems + pageSize - 1) / pageSize;
    }
}