package edu.aitu.oop3.repositories;
import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.entities.Progress;
import edu.aitu.oop3.exceptions.DatabaseException;
import edu.aitu.oop3.repositories.ProgressRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgressRepositoryImpl implements ProgressRepository {
    private final IDB db;

    public ProgressRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Progress markCompleted(long userId, long lessonId) {
        String sql = """
            insert into progress(user_id, lesson_id, completed)
            values (?, ?, true)
            on conflict (user_id, lesson_id)
            do update set completed=true
            returning id
            """;
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, lessonId);

            ResultSet rs = st.executeQuery();
            rs.next();
            return new Progress(rs.getLong(1), userId, lessonId, true);

        } catch (SQLException e) {
            throw new DatabaseException("Update progress failed", e);
        }
    }

    @Override
    public Optional<Progress> find(long userId, long lessonId) {
        String sql = "select id, completed from progress where user_id=? and lesson_id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, lessonId);

            ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();

            return Optional.of(
                    new Progress(rs.getLong("id"), userId, lessonId, rs.getBoolean("completed"))
            );

        } catch (SQLException e) {
            throw new DatabaseException("Find progress failed", e);
        }
    }

    @Override
    public List<Progress> findByUserAndCourse(long userId, long courseId) {
        String sql = """
            select p.id, p.lesson_id, p.completed
            from progress p
            join lessons l on p.lesson_id = l.id
            where p.user_id=? and l.course_id=?
            """;
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, courseId);

            ResultSet rs = st.executeQuery();
            List<Progress> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Progress(
                        rs.getLong("id"),
                        userId,
                        rs.getLong("lesson_id"),
                        rs.getBoolean("completed")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseException("Load progress failed", e);
        }
    }
}
