package edu.aitu.oop3.repositories;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.entities.Lesson;
import edu.aitu.oop3.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonRepositoryImpl implements LessonRepository {
    private final IDB db;

    public LessonRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Lesson create(Lesson lesson) {
        String sql = "insert into lessons (course_id, title, content) values (1,2,3) returning id";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, lesson.getCourseId());
            st.setString(2, lesson.getTitle());
            st.setString(3, lesson.getContent());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) lesson.setId(rs.getLong(1));
            }
            return lesson;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: create lesson", e);
        }
    }

    @Override
    public Optional<Lesson> findById(long id) {
        String sql = "select id, course_id, title, content from lessons where id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("DB error: find lesson", e);
        }
    }

    @Override
    public List<Lesson> findByCourse(long courseId) {
        String sql = "select id, course_id, title, content from lessons where course_id=? order by id";
        List<Lesson> list = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, courseId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: list lessons", e);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from lessons where id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: delete lesson", e);
        }
    }

    private Lesson map(ResultSet rs) throws SQLException {
        return new Lesson(
                rs.getLong("id"),
                rs.getLong("course_id"),
                rs.getString("title"),
                rs.getString("content")
        );
    }
}
