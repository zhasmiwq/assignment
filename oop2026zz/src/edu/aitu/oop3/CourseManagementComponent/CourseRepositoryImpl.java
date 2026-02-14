package edu.aitu.oop3.CourseManagementComponent;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {
    private final IDB db;

    public CourseRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Course create(Course course) {
        String sql = "insert into courses(title, description, teacher_id) values (?,?,?) returning id";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, course.getTitle());
            st.setString(2, course.getDescription());
            st.setLong(3, (Long) course.getTeacherId());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) course.setId(rs.getLong(1));
            }
            return course;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: create course", e);
        }
    }

    @Override
    public Optional<Course> findById(long id) {
        String sql = "select id, title, description, teacher_id from courses where id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("DB error: find course", e);
        }
    }

    @Override
    public List<Course> findAll() {
        String sql = "select id, title, description, teacher_id from courses order by id";
        List<Course> list = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: list courses", e);
        }
    }

    @Override
    public List<Course> findByTeacher(long teacherId) {
        String sql = "select id, title, description, teacher_id from courses where teacher_id=? order by id";
        List<Course> list = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, teacherId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: list courses by teacher", e);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from courses where id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: delete course", e);
        }
    }

    private Course map(ResultSet rs) throws SQLException {
        return Course.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .teacherId(rs.getLong("teacher_id"))
                .build();
    }
}
