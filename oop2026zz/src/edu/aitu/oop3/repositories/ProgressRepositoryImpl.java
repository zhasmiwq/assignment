package edu.aitu.oop3.repositories;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgressRepositoryImpl implements ProgressRepository {
    private final IDB db;

    public ProgressRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void markCompleted(long userId, long lessonId) {
        String sql = "insert into progress(user_id, lesson_id, completed) values (?, ?, true) " +
                "on conflict (user_id, lesson_id) do update set completed = true";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, lessonId);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("DB error: mark completed", e);
        }
    }
}
